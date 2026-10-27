#!/usr/bin/env bash
# ============================================================
# 墨阅书城 · 部署自检
# 用法：bash deploy/verify.sh
# 逐项验证"数据是否真的导进去了、页面是否真的能打开"。
# 只看容器是否 Up 是不够的 —— 初始化失败后容器照样会健康运行。
# ============================================================
set -uo pipefail

MYSQL_C="moyue-mysql"
MYSQL_P="${MYSQL_ROOT_PASSWORD:-novelplus2026}"
PASS=0
FAIL=0

# ⚠ 不要用 -o /dev/null：Windows 版 curl 写不了 /dev/null，
#   会返回退出码 23（Failed writing body），导致"服务未就绪"的误判。
TMP_OUT="$(mktemp 2>/dev/null || echo "./_verify_body.$$")"
trap 'rm -f -- "$TMP_OUT"' EXIT

ok()   { echo "  [通过] $1"; PASS=$((PASS + 1)); }
bad()  { echo "  [失败] $1"; FAIL=$((FAIL + 1)); }

q() {
  docker exec "$MYSQL_C" mysql -uroot -p"$MYSQL_P" --default-character-set=utf8mb4 -N -B -e "$1" 2>/dev/null | tr -d '\r'
}

echo "=== ① 小说基础数据 ==="
BOOKS=$(q "select count(*) from novel_plus.book;")
IDX=$(q "select count(*) from novel_plus.book_index;")
[ -n "${BOOKS:-}" ] && [ "$BOOKS" -gt 0 ] && ok "book 表 $BOOKS 条" || bad "book 表为空（初始数据未导入）"
[ -n "${IDX:-}" ] && [ "$IDX" -gt 0 ] && ok "book_index 表 $IDX 条" || bad "book_index 表为空"

echo "=== ② 分表正文（ShardingSphere 的目标表）==="
CONTENT=$(q "select (select count(*) from novel_plus.book_content0)+(select count(*) from novel_plus.book_content1)+(select count(*) from novel_plus.book_content2)+(select count(*) from novel_plus.book_content3)+(select count(*) from novel_plus.book_content4)+(select count(*) from novel_plus.book_content5)+(select count(*) from novel_plus.book_content6)+(select count(*) from novel_plus.book_content7)+(select count(*) from novel_plus.book_content8)+(select count(*) from novel_plus.book_content9);")
[ -n "${CONTENT:-}" ] && [ "$CONTENT" -gt 0 ] && ok "book_content0~9 合计 $CONTENT 条正文" || bad "分表无正文，章节页会空白"

echo "=== ③ 增量脚本是否执行 ==="
CR=$(q "select count(*) from information_schema.tables where table_schema='novel_plus' and table_name='comment_report';")
UB=$(q "select count(*) from information_schema.tables where table_schema='novel_plus' and table_name='user_bookmark';")
LC=$(q "select count(*) from information_schema.columns where table_schema='novel_plus' and table_name='book_comment' and column_name='like_count';")
[ "${CR:-0}" = "1" ] && ok "comment_report 表存在（20261024 增量已执行）" || bad "comment_report 表缺失 —— 增量脚本没跑成功"
[ "${UB:-0}" = "1" ] && ok "user_bookmark 表存在（20261023 增量已执行）" || bad "user_bookmark 表缺失"
[ "${LC:-0}" = "1" ] && ok "book_comment.like_count 字段存在" || bad "book_comment.like_count 缺失"

echo "=== ④ 服务可访问性 ==="
# 容器 Started 不代表端口已监听：Spring Boot 启动需要十几秒，
# 刚 up 完立刻探测会得到 HTTP 000（连接被拒），所以先等就绪
wait_ready() {
  local port=$1 i
  for i in $(seq 1 30); do
    curl -s -o "$TMP_OUT" --max-time 3 "http://localhost:${port}/" && return 0
    sleep 2
  done
  return 1
}

for item in "前台 8083 /" "后台 8089 /login" "采集 8081 /"; do
  name=${item%% *}; rest=${item#* }
  port=${rest%% *}; path=${rest#* }
  wait_ready "$port" || echo "  （$name :$port 等待 60s 仍未监听）"
  code=$(curl -s -o "$TMP_OUT" -w '%{http_code}' --max-time 15 "http://localhost:${port}${path}" 2>/dev/null)
  case "$code" in
    200|302|401) ok "$name :$port$path -> HTTP $code" ;;
    *)           bad "$name :$port$path -> HTTP ${code:-无响应}" ;;
  esac
done

echo "=== ⑤ 章节页能否读出正文（真正走分片路由）==="
CH=$(q "select concat(b.id,'/',i.id) from novel_plus.book b join novel_plus.book_index i on i.book_id=b.id where b.id=(select book_id from novel_plus.book_index group by book_id order by count(*) desc limit 1) order by i.index_num limit 1;")
if [ -n "${CH:-}" ]; then
  code=$(curl -s -o "$TMP_OUT" -w '%{http_code}' --max-time 15 "http://localhost:8083/book/${CH}.html")
  [ "$code" = "200" ] && ok "章节页 /book/${CH}.html -> HTTP 200" || bad "章节页 /book/${CH}.html -> HTTP $code"
else
  bad "取不到测试章节"
fi

echo "=== ⑥ 站点品牌（数据包里存的是原始站点信息，需被覆盖）==="
NAME=$(q "select name from novel_plus.website_info where id=1;")
[ "$NAME" = "墨阅书城" ] && ok "站点名 = $NAME" || bad "站点名 = ${NAME:-空}（品牌覆盖脚本 02-brand.sql 未生效）"
FL=$(q "select count(*) from novel_plus.friend_link where link_url like '%xxyopen%' or link_name like '%小说精品屋%';")
[ "${FL:-1}" = "0" ] && ok "友情链接无原始站点条目" || bad "友情链接仍有 $FL 条原始站点信息"
if curl -s -o "$TMP_OUT" --max-time 15 http://localhost:8083/; then
  TITLE=$(grep -o "<title>[^<]*</title>" "$TMP_OUT" | head -1)
  case "$TITLE" in
    *墨阅书城*) ok "首页标题 $TITLE" ;;
    *)          bad "首页标题 $TITLE" ;;
  esac
fi

echo
echo "----------------------------------------"
echo "通过 $PASS 项，失败 $FAIL 项"
[ "$FAIL" -eq 0 ] && echo "结论：部署正常" || echo "结论：存在异常，先执行 docker compose down -v 重新初始化"
exit "$FAIL"
