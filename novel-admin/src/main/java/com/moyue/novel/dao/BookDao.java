package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.BookDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 小说表
 *
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:46
 */
@Mapper
public interface BookDao {

    BookDO get(Long id);

    List<BookDO> list(@SanitizeMap Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BookDO book);

    int update(BookDO book);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Map<Object, Object>> tableSta(Date minDate);

    List<BookDO> batchGet(List<Long> ids);

}
