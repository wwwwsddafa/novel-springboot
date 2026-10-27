package com.moyue.novel.mapper;

import com.moyue.novel.entity.BookIndex;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface CrawlBookIndexMapper extends BookIndexMapper {


    /**
     * 查询最后的章节
     * */
    BookIndex queryLastIndex(@Param("bookId") Long bookId);
}
