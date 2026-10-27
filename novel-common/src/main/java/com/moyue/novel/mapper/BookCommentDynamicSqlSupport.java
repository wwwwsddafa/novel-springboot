package com.moyue.novel.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BookCommentDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BookComment bookComment = new BookComment();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bookComment.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> bookId = bookComment.bookId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> commentContent = bookComment.commentContent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> location = bookComment.location;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> replyCount = bookComment.replyCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> likeCount = bookComment.likeCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> auditStatus = bookComment.auditStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = bookComment.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUserId = bookComment.createUserId;

    public static final SqlColumn<Long> indexId = bookComment.indexId;

    public static final SqlColumn<String> paragraphId = bookComment.paragraphId;

    public static final SqlColumn<Byte> commentType = bookComment.commentType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BookComment extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> bookId = column("book_id", JDBCType.BIGINT);

        public final SqlColumn<String> commentContent = column("comment_content", JDBCType.VARCHAR);

        public final SqlColumn<String> location = column("location", JDBCType.VARCHAR);

        public final SqlColumn<Integer> replyCount = column("reply_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> likeCount = column("like_count", JDBCType.INTEGER);

        public final SqlColumn<Byte> auditStatus = column("audit_status", JDBCType.TINYINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUserId = column("create_user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> indexId = column("index_id", JDBCType.BIGINT);

        public final SqlColumn<String> paragraphId = column("paragraph_id", JDBCType.VARCHAR);

        public final SqlColumn<Byte> commentType = column("comment_type", JDBCType.TINYINT);

        public BookComment() {
            super("book_comment");
        }
    }
}