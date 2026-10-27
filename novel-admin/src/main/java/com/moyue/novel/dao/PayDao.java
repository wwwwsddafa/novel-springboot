package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.PayDO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 充值订单
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:57
 */
@Mapper
public interface PayDao {

	PayDO get(Long id);
	
	List<PayDO> list(@SanitizeMap Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PayDO pay);
	
	int update(PayDO pay);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<Map<Object, Object>> tableSta(Date minDate);
}
