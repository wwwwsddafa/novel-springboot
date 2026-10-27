package com.moyue.novel.service;

import com.moyue.novel.domain.FriendLinkDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-14 15:12:25
 */
public interface FriendLinkService {
	
	FriendLinkDO get(Integer id);
	
	List<FriendLinkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FriendLinkDO friendLink);
	
	int update(FriendLinkDO friendLink);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
