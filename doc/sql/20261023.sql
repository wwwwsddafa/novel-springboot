-- ----------------------------
-- Table structure for user_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `user_bookmark`;
CREATE TABLE `user_bookmark`
(
    `id`            bigint(20)   NOT NULL COMMENT '主键',
    `user_id`       bigint(20)   NOT NULL COMMENT '用户ID',
    `book_id`       bigint(20)   NOT NULL COMMENT '小说ID',
    `index_id`      bigint(20)   NOT NULL COMMENT '章节目录ID',
    `bookmark_name` varchar(50)  DEFAULT NULL COMMENT '书签名称',
    `position`      int(11)      DEFAULT NULL COMMENT '章节内偏移量',
    `create_time`   datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `key_uq_userid_bookid_indexid` (`user_id`, `book_id`, `index_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户书签表';