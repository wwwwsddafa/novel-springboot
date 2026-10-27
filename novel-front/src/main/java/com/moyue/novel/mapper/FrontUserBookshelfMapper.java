package com.moyue.novel.mapper;

import com.moyue.novel.vo.BookShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserBookshelfMapper extends UserBookshelfMapper {

    List<BookShelfVO> listBookShelf(@Param("userId") Long userId);

}
