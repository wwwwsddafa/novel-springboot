/**
 * 
 */
package com.moyue.common.service;

import java.util.List;
import java.util.Map;

import com.moyue.common.domain.GenColumnsDO;
import org.springframework.stereotype.Service;

/**
 * @author 王聪
 * @Time 2019-10-20 11:23:09
 * @description
 * 
 */
@Service
public interface GeneratorService {
	List<Map<String, Object>> list(String tableName);

	void generatorCode(String[] tableNames);

	byte[] downloadCode(String[] tableNames);


    List<GenColumnsDO> listColumnsByTableName(String tableName);

    boolean genColumnsSave(List<GenColumnsDO> list);
}
