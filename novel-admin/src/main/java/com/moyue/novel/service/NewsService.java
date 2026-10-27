package com.moyue.novel.service;

import com.moyue.novel.domain.NewsDO;

import java.util.List;
import java.util.Map;

/**
 * 新闻表
 * 
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2020-12-01 10:05:51
 */
public interface NewsService {
	
	NewsDO get(Long id);
	
	List<NewsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NewsDO news);
	
	int update(NewsDO news);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
