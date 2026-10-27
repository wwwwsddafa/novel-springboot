package com.moyue.novel.mapper;

import com.moyue.novel.vo.BookCommentReplyVO;
import com.moyue.novel.vo.BookCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontBookCommentReplyMapper extends BookCommentReplyMapper {

    List<BookCommentReplyVO> listCommentReplyByPage(@Param("userId") Long userId, @Param("commentId") Long commentId);

}
