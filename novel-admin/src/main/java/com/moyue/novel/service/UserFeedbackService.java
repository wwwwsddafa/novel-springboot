package com.moyue.novel.service;

import com.moyue.novel.domain.UserFeedbackDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-18 11:08:54
 */
public interface UserFeedbackService {
	
	UserFeedbackDO get(Long id);
	
	List<UserFeedbackDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserFeedbackDO userFeedback);
	
	int update(UserFeedbackDO userFeedback);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
