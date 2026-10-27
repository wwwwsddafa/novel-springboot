package com.moyue.common.dao;

import com.moyue.common.annotation.SanitizeMap;
import com.moyue.common.domain.LogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2019-10-03 15:45:42
 */
@Mapper
public interface LogDao {

	LogDO get(Long id);
	
	List<LogDO> list(@SanitizeMap Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LogDO log);
	
	int update(LogDO log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
