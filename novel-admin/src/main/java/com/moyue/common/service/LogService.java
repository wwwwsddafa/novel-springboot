package com.moyue.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moyue.common.domain.LogDO;
import com.moyue.common.domain.PageDO;
import com.moyue.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
