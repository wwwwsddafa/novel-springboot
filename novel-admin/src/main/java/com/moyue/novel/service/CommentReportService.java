package com.moyue.novel.service;

import com.moyue.novel.domain.CommentReportDO;
import java.util.List;
import java.util.Map;

public interface CommentReportService {

    CommentReportDO get(Long id);

    List<CommentReportDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int update(CommentReportDO commentReport);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int handleReport(Long id, Byte status, String handlerNote);
}