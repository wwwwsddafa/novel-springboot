package com.moyue.novel.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CommentReportDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final CommentReport commentReport = new CommentReport();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = commentReport.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> targetType = commentReport.targetType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> targetId = commentReport.targetId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> reporterId = commentReport.reporterId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> reportReason = commentReport.reportReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> reportType = commentReport.reportType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = commentReport.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> handlerNote = commentReport.handlerNote;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = commentReport.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = commentReport.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class CommentReport extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Byte> targetType = column("target_type", JDBCType.TINYINT);

        public final SqlColumn<Long> targetId = column("target_id", JDBCType.BIGINT);

        public final SqlColumn<Long> reporterId = column("reporter_id", JDBCType.BIGINT);

        public final SqlColumn<String> reportReason = column("report_reason", JDBCType.VARCHAR);

        public final SqlColumn<String> reportType = column("report_type", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<String> handlerNote = column("handler_note", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public CommentReport() {
            super("comment_report");
        }
    }
}