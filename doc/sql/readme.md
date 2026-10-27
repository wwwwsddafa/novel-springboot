## 目录说明

| 文件 | 用途 |
| --- | --- |
| `novel_plus.sql` | 完整表结构，**不含小说数据**（约 156KB） |
| `novel_plus_data.sql.zip` | 完整数据转储（含小说正文），压缩包 74MB / 解压后 177MB |
| `yyyyMMdd.sql` | 版本升级增量脚本，按日期先后顺序执行 |
| `brand.sql` | 站点品牌信息覆盖（站名、域名、SEO、Logo、联系方式） |
| `Dockerfile.data` | 构建"自带初始数据的 MySQL 镜像"，自动解压数据包并按序执行全部脚本 |
| `Dockerfile` | 旧版镜像定义（要求手工把解压后的 SQL 放在同目录） |

## 手工初始化（不使用 Docker）

两种方式二选一，**不要混用**：

1. 只要空站：执行 `novel_plus.sql`。
2. 要带小说数据的站：解压 `novel_plus_data.sql.zip` → 执行其中的 `novel_plus_data.sql` → 再按日期顺序执行所有 `2026*.sql` 增量 → 最后执行 `brand.sql`。

## 升级已有库

按当前代码版本对应的日期，依次执行该日期之后新增的增量 SQL 文件。

## 用 Docker 初始化（推荐）

`Dockerfile.data` 已把上述流程固化：构建时解压数据包生成 `00-data.sql`，把增量脚本合并为 `01-increments.sql`（开头补 `USE novel_plus;`，否则会报 `ERROR 1046 No database selected`），最后执行 `02-brand.sql`。容器首次启动自动完成，无需手工操作。

```bash
# 直接使用已发布的镜像，无需本地构建
docker compose up -d
bash deploy/verify.sh        # 验证数据、增量、品牌、页面是否都正常

# 改过脚本要自己重建镜像时
docker compose -f docker-compose.yml -f docker-compose.build.yml up -d --build
```

> ⚠ 增量脚本里任何一条语句失败都会中断整个初始化流程；而容器随后会自动重启，
> 此时数据目录已非空，initdb 被跳过，最终得到一个"健康"但不完整的库。
> 修改增量脚本后务必重新执行 `down -v` 并跑一次 `deploy/verify.sh`。
