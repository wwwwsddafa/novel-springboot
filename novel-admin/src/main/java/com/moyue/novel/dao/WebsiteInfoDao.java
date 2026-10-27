package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.WebsiteInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 网站信息表
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-14 11:05:43
 */
@Mapper
public interface WebsiteInfoDao {

	WebsiteInfoDO get(Long id);
	
	List<WebsiteInfoDO> list(@SanitizeMap Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WebsiteInfoDO websiteInfo);
	
	int update(WebsiteInfoDO websiteInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
