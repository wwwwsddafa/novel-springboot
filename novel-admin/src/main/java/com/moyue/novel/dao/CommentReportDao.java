package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;
import com.moyue.novel.domain.CommentReportDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentReportDao {

    CommentReportDO get(Long id);

    List<CommentReportDO> list(@SanitizeMap Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CommentReportDO commentReport);

    int update(CommentReportDO commentReport);

    int remove(Long id);

    int batchRemove(Long[] ids);
}