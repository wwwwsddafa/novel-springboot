package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.BookContentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 小说内容表
 *
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:52:06
 */
@Mapper
public interface BookContentDao {

    BookContentDO get(Long id);

    List<BookContentDO> list(@SanitizeMap Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BookContentDO bookContent);

    int update(BookContentDO bookContent);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int removeByIndexIds(Long[] indexIds);

    List<BookContentDO> listByIndexIds(@Param("indexIds") List<Long> indexIds);
}
