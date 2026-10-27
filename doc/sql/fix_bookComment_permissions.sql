-- ============================================
-- 修复评论管理缺失的按钮权限
-- 在 novel_plus 数据库中执行
-- ============================================

-- 1. 补齐评论管理按钮权限（detail/add/edit/remove/batchRemove）
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '查看', '', 'novel:bookComment:detail', 2, '', 0
FROM sys_menu m WHERE m.perms = 'novel:bookComment:bookComment' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:bookComment:detail');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '新增', '', 'novel:bookComment:add', 2, '', 1
FROM sys_menu m WHERE m.perms = 'novel:bookComment:bookComment' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:bookComment:add');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '修改', '', 'novel:bookComment:edit', 2, '', 2
FROM sys_menu m WHERE m.perms = 'novel:bookComment:bookComment' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:bookComment:edit');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '删除', '', 'novel:bookComment:remove', 2, '', 3
FROM sys_menu m WHERE m.perms = 'novel:bookComment:bookComment' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:bookComment:remove');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '批量删除', '', 'novel:bookComment:batchRemove', 2, '', 4
FROM sys_menu m WHERE m.perms = 'novel:bookComment:bookComment' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:bookComment:batchRemove');

-- 2. 将新增的权限分配给管理员角色（role_id=1，即超级管理员）
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, m.menu_id
FROM sys_menu m
WHERE m.perms IN (
    'novel:bookComment:detail',
    'novel:bookComment:add',
    'novel:bookComment:edit',
    'novel:bookComment:remove',
    'novel:bookComment:batchRemove'
)
AND NOT EXISTS (
    SELECT 1 FROM sys_role_menu rm WHERE rm.role_id = 1 AND rm.menu_id = m.menu_id
);