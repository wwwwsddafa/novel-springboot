package com.moyue.system.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.system.domain.MenuDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2019-10-03 09:45:09
 */
@Mapper
public interface MenuDao {

	MenuDO get(Long menuId);
	
	List<MenuDO> list(@SanitizeMap Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
