/* ============================================
   评论举报机制 - 数据库变更
   执行日期：2026-10-24
   ============================================ */

/* 1. 评论举报表 */
CREATE TABLE `comment_report` (
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `target_type`   TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '举报目标类型 1=评论 2=回复',
    `target_id`     BIGINT(20)   NOT NULL COMMENT '被举报的评论/回复ID',
    `reporter_id`   BIGINT(20)   NOT NULL COMMENT '举报人ID',
    `report_reason` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '举报描述',
    `report_type`   VARCHAR(30)  NOT NULL DEFAULT 'other' COMMENT '举报类别: spam/abuse/porn/other',
    `status`        TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '处理状态: 0=待处理 1=已忽略 2=已删除',
    `handler_note`  VARCHAR(500) DEFAULT '' COMMENT '管理员处理备注',
    `create_time`   DATETIME     DEFAULT NULL COMMENT '举报时间',
    `update_time`   DATETIME     DEFAULT NULL COMMENT '处理时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_reporter_target` (`reporter_id`, `target_type`, `target_id`),
    KEY `idx_target` (`target_type`, `target_id`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论举报表';

/* 2. 补齐评论管理缺失的按钮权限 */
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

/* 3. 新增举报管理菜单，挂在内容管理下 */
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '举报管理', 'novel/commentReport/commentReport', 'novel:commentReport:commentReport', 1, 'fa fa-flag', 1
FROM sys_menu m
WHERE m.name = '内容管理' AND m.type = 0
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:commentReport:commentReport');

/* 4. 举报管理按钮权限 */
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '处理', '', 'novel:commentReport:handle', 2, '', 0
FROM sys_menu m WHERE m.perms = 'novel:commentReport:commentReport' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:commentReport:handle');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '删除', '', 'novel:commentReport:remove', 2, '', 1
FROM sys_menu m WHERE m.perms = 'novel:commentReport:commentReport' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:commentReport:remove');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '批量删除', '', 'novel:commentReport:batchRemove', 2, '', 2
FROM sys_menu m WHERE m.perms = 'novel:commentReport:commentReport' AND m.type = 1
AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'novel:commentReport:batchRemove');

/* 5. 将所有新增权限分配给管理员角色(role_id=1) */
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, m.menu_id
FROM sys_menu m
WHERE m.perms IN (
    'novel:bookComment:detail',
    'novel:bookComment:add',
    'novel:bookComment:edit',
    'novel:bookComment:remove',
    'novel:bookComment:batchRemove',
    'novel:commentReport:commentReport',
    'novel:commentReport:handle',
    'novel:commentReport:remove',
    'novel:commentReport:batchRemove'
)
AND NOT EXISTS (
    SELECT 1 FROM sys_role_menu rm WHERE rm.role_id = 1 AND rm.menu_id = m.menu_id
);