package com.moyue.novel.mapper;

import com.moyue.novel.vo.BookCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface FrontBookCommentMapper extends BookCommentMapper {

    List<BookCommentVO> listCommentByPage(@Param("userId") Long userId, @Param("bookId") Long bookId, @Param("sort") String sort);

    void addReplyCount(@Param("commentId") Long commentId);

    List<BookCommentVO> listInlineComments(@Param("indexId") Long indexId, @Param("paragraphId") String paragraphId);

    List<Map<String, Object>> listParagraphCommentCounts(@Param("indexId") Long indexId);
}