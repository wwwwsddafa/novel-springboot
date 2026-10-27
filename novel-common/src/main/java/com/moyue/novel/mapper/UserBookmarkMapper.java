package com.moyue.novel.mapper;

import com.moyue.novel.entity.UserBookmark;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.moyue.novel.mapper.UserBookmarkDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserBookmarkMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, bookId, indexId, bookmarkName, position, createTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserBookmark> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserBookmark> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserBookmarkResult")
    Optional<UserBookmark> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserBookmarkResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="book_id", property="bookId", jdbcType=JdbcType.BIGINT),
        @Result(column="index_id", property="indexId", jdbcType=JdbcType.BIGINT),
        @Result(column="bookmark_name", property="bookmarkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="position", property="position", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserBookmark> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserBookmark record) {
        return MyBatis3Utils.insert(this::insert, record, userBookmark, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(bookId).toProperty("bookId")
            .map(indexId).toProperty("indexId")
            .map(bookmarkName).toProperty("bookmarkName")
            .map(position).toProperty("position")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserBookmark> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userBookmark, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(bookId).toProperty("bookId")
            .map(indexId).toProperty("indexId")
            .map(bookmarkName).toProperty("bookmarkName")
            .map(position).toProperty("position")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserBookmark record) {
        return MyBatis3Utils.insert(this::insert, record, userBookmark, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(bookId).toPropertyWhenPresent("bookId", record::getBookId)
            .map(indexId).toPropertyWhenPresent("indexId", record::getIndexId)
            .map(bookmarkName).toPropertyWhenPresent("bookmarkName", record::getBookmarkName)
            .map(position).toPropertyWhenPresent("position", record::getPosition)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserBookmark> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserBookmark> selectMany(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserBookmark> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserBookmark> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userBookmark, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserBookmark record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
            .set(userId).equalTo(record::getUserId)
            .set(bookId).equalTo(record::getBookId)
            .set(indexId).equalTo(record::getIndexId)
            .set(bookmarkName).equalTo(record::getBookmarkName)
            .set(position).equalTo(record::getPosition)
            .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserBookmark record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
            .set(userId).equalToWhenPresent(record::getUserId)
            .set(bookId).equalToWhenPresent(record::getBookId)
            .set(indexId).equalToWhenPresent(record::getIndexId)
            .set(bookmarkName).equalToWhenPresent(record::getBookmarkName)
            .set(position).equalToWhenPresent(record::getPosition)
            .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserBookmark record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(bookId).equalTo(record::getBookId)
            .set(indexId).equalTo(record::getIndexId)
            .set(bookmarkName).equalTo(record::getBookmarkName)
            .set(position).equalTo(record::getPosition)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserBookmark record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(bookId).equalToWhenPresent(record::getBookId)
            .set(indexId).equalToWhenPresent(record::getIndexId)
            .set(bookmarkName).equalToWhenPresent(record::getBookmarkName)
            .set(position).equalToWhenPresent(record::getPosition)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}