/* ============================================================
   划线评论（段落锚点法）数据库变更
   日期：2026-10-26
   ============================================================ */

/* 1. 新增3个字段 */
ALTER TABLE book_comment
  ADD COLUMN index_id BIGINT DEFAULT NULL COMMENT '章节ID，FK→book_index.id' AFTER book_id,
  ADD COLUMN paragraph_id VARCHAR(20) DEFAULT NULL COMMENT '段落标识，如P003' AFTER location,
  ADD COLUMN comment_type TINYINT DEFAULT 1 COMMENT '评论类型：1=书级评论 2=划线评论' AFTER paragraph_id;

/* 2. 索引改造：书级评论由应用层保证每用户每书1条，划线评论不限 */
/* 删掉旧的UNIQUE索引（它阻止同一用户发多条划线评论）
   注意：不同版本的库里这个索引名不一致（key_uq_bookid_userid / key_uq_bookid_userid_type），
   直接 DROP 会因 1091 报错中断整个初始化流程，这里按实际存在的名字动态删除。 */
SET @old_idx := (SELECT index_name FROM information_schema.statistics
                 WHERE table_schema = DATABASE() AND table_name = 'book_comment'
                   AND non_unique = 0
                   AND index_name IN ('key_uq_bookid_userid_type', 'key_uq_bookid_userid')
                 LIMIT 1);
SET @ddl := IF(@old_idx IS NULL, 'SELECT 1',
               CONCAT('ALTER TABLE book_comment DROP INDEX `', @old_idx, '`'));
PREPARE stmt FROM @ddl;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
/* 建普通索引（非UNIQUE），允许同一用户多条划线评论 */
ALTER TABLE book_comment ADD INDEX idx_bookid_userid_type_pid (book_id, create_user_id, comment_type, paragraph_id);

/* 3. 性能索引：按章节+段落查划线评论 */
ALTER TABLE book_comment ADD INDEX idx_index_paragraph (index_id, paragraph_id);