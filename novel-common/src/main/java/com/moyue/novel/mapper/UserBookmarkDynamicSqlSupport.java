package com.moyue.novel.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserBookmarkDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserBookmark userBookmark = new UserBookmark();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = userBookmark.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = userBookmark.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> bookId = userBookmark.bookId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> indexId = userBookmark.indexId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bookmarkName = userBookmark.bookmarkName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> position = userBookmark.position;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = userBookmark.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserBookmark extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> bookId = column("book_id", JDBCType.BIGINT);

        public final SqlColumn<Long> indexId = column("index_id", JDBCType.BIGINT);

        public final SqlColumn<String> bookmarkName = column("bookmark_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> position = column("position", JDBCType.INTEGER);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UserBookmark() {
            super("user_bookmark");
        }
    }
}