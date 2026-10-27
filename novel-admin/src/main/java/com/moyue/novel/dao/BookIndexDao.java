package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.BookIndexDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 小说目录表
 *
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:51:54
 */
@Mapper
public interface BookIndexDao {

    BookIndexDO get(Long id);

    List<BookIndexDO> list(@SanitizeMap Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BookIndexDO bookIndex);

    int update(BookIndexDO bookIndex);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> getIdsByBookId(Long bookId);
}
