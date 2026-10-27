package com.moyue.novel.mapper;

import com.moyue.novel.mapper.UserBookmarkMapper;
import com.moyue.novel.vo.BookmarkVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FrontUserBookmarkMapper extends UserBookmarkMapper {

    List<BookmarkVO> listBookmarkByBook(@Param("userId") Long userId, @Param("bookId") Long bookId);

    List<BookmarkVO> listAllBookmark(@Param("userId") Long userId);

}
