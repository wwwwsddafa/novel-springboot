package com.moyue.novel.mapper;

import static com.moyue.novel.mapper.CommentReportDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.moyue.novel.entity.CommentReport;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CommentReportMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, targetType, targetId, reporterId, reportReason, reportType, status, handlerNote, createTime, updateTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<CommentReport> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CommentReport> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("CommentReportResult")
    Optional<CommentReport> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "CommentReportResult", value = {
        @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "target_type", property = "targetType", jdbcType = JdbcType.TINYINT),
        @Result(column = "target_id", property = "targetId", jdbcType = JdbcType.BIGINT),
        @Result(column = "reporter_id", property = "reporterId", jdbcType = JdbcType.BIGINT),
        @Result(column = "report_reason", property = "reportReason", jdbcType = JdbcType.VARCHAR),
        @Result(column = "report_type", property = "reportType", jdbcType = JdbcType.VARCHAR),
        @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
        @Result(column = "handler_note", property = "handlerNote", jdbcType = JdbcType.VARCHAR),
        @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<CommentReport> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, commentReport, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, commentReport, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(CommentReport record) {
        return MyBatis3Utils.insert(this::insert, record, commentReport, c ->
            c.map(id).toProperty("id")
                .map(targetType).toProperty("targetType")
                .map(targetId).toProperty("targetId")
                .map(reporterId).toProperty("reporterId")
                .map(reportReason).toProperty("reportReason")
                .map(reportType).toProperty("reportType")
                .map(status).toProperty("status")
                .map(handlerNote).toProperty("handlerNote")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(CommentReport record) {
        return MyBatis3Utils.insert(this::insert, record, commentReport, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
                .map(targetType).toPropertyWhenPresent("targetType", record::getTargetType)
                .map(targetId).toPropertyWhenPresent("targetId", record::getTargetId)
                .map(reporterId).toPropertyWhenPresent("reporterId", record::getReporterId)
                .map(reportReason).toPropertyWhenPresent("reportReason", record::getReportReason)
                .map(reportType).toPropertyWhenPresent("reportType", record::getReportType)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(handlerNote).toPropertyWhenPresent("handlerNote", record::getHandlerNote)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<CommentReport> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, commentReport, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<CommentReport> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, commentReport, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, commentReport, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(CommentReport record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(targetType).equalTo(record::getTargetType)
                .set(targetId).equalTo(record::getTargetId)
                .set(reporterId).equalTo(record::getReporterId)
                .set(reportReason).equalTo(record::getReportReason)
                .set(reportType).equalTo(record::getReportType)
                .set(status).equalTo(record::getStatus)
                .set(handlerNote).equalTo(record::getHandlerNote)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(CommentReport record) {
        return update(c ->
            c.set(targetType).equalToWhenPresent(record::getTargetType)
                .set(targetId).equalToWhenPresent(record::getTargetId)
                .set(reporterId).equalToWhenPresent(record::getReporterId)
                .set(reportReason).equalToWhenPresent(record::getReportReason)
                .set(reportType).equalToWhenPresent(record::getReportType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(handlerNote).equalToWhenPresent(record::getHandlerNote)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
        );
    }
}