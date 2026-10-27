package com.moyue.system.service;

import com.moyue.common.domain.DictDO;
import com.moyue.common.domain.Tree;
import com.moyue.system.domain.DataPermDO;

import java.util.List;
import java.util.Map;

/**
 * 数据权限管理
 * 
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:40:03
 */
public interface DataPermService {
	
	DataPermDO get(Long id);
	
	List<DataPermDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DataPermDO dataPerm);
	
	int update(DataPermDO dataPerm);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<DataPermDO> listModuleName();

    Tree<DataPermDO> getTree();

    Tree<DataPermDO> getTree(Long roleId);
}
