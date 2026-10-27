-- ============================================
-- 评论排序策略 - 数据库变更
-- 执行日期：2026-10-24
-- ============================================

-- 1. book_comment 表新增点赞计数字段
ALTER TABLE book_comment 
ADD COLUMN like_count INT NOT NULL DEFAULT 0 COMMENT '点赞数' AFTER reply_count;

-- 2. 新增索引：按书籍+热度排序
ALTER TABLE book_comment 
ADD INDEX idx_bookid_like (book_id, like_count DESC);