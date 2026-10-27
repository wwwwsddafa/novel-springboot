package com.moyue.novel.mapper;

import com.moyue.novel.vo.BookSettingVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontBookSettingMapper extends BookSettingMapper {

    List<BookSettingVO> listVO();
}
