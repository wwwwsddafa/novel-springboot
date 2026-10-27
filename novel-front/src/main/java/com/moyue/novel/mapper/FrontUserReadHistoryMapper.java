package com.moyue.novel.mapper;

import com.moyue.novel.vo.BookReadHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserReadHistoryMapper extends UserReadHistoryMapper {

    List<BookReadHistoryVO> listReadHistory(@Param("userId") Long userId);

}
