-- ============================================================
-- 站点品牌信息覆盖脚本
-- ------------------------------------------------------------
-- 数据包 novel_plus_data.sql 是带演示数据的完整转储，
-- 其中的 website_info / friend_link / author 保留了原始站点信息
-- （站名、域名、SEO 关键词、Logo 地址、作者 QQ）。
-- 本脚本在初始化时最后执行，把这些字段改成本项目的信息。
--
-- 说明：只清理"指向原始项目/作者"的字段，
--      表格结构、小说内容、演示作者笔名等业务数据保持不动。
-- ============================================================
SET NAMES utf8mb4;
USE `novel_plus`;

-- 1. 站点信息：站名 / 域名 / SEO / Logo / 联系方式
UPDATE website_info
SET name        = '墨阅书城',
    domain      = 'www.moyue.com',
    keyword     = '墨阅书城,小说,在线阅读,网络文学,书城,精品小说',
    description = '墨阅书城是一个支持 PC 与移动端阅读的原创文学平台，提供作品推荐、分类检索、排行榜、在线阅读、评论互动、作家专区与会员订阅等功能。',
    qq          = '',
    logo        = '/images/logo.png',
    logo_dark   = '/images/logo_white.png'
WHERE id = 1;

-- 2. 友情链接：移除指向原始站点的条目
DELETE FROM friend_link WHERE link_name = '小说精品屋' OR link_url LIKE '%xxyopen%';

-- 3. 演示作者数据中的原始联系账号（作者本人的 QQ / 邮箱）
UPDATE author SET chat_account = '', email = ''
WHERE chat_account = '1179705413' OR email LIKE '%1179705413%';

-- 4. 后台管理员密码
--    数据包里的 admin 密码是原作者私设的，无法从哈希反推，
--    这里统一重置为 admin888，保证拿到镜像的人开箱即可登录后台。
--    哈希算法见 com.moyue.common.utils.MD5Utils：
--    Shiro SimpleHash("md5", "admin888", salt="admin"+"1qazxsw2", iterations=2)
UPDATE sys_user
SET password = '56aa8af4061807d8adc6d1b39d5e8fe0'
WHERE username = 'admin';
