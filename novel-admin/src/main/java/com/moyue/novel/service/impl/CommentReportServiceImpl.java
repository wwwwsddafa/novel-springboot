package com.moyue.novel.service.impl;

import com.moyue.novel.dao.CommentReportDao;
import com.moyue.novel.domain.CommentReportDO;
import com.moyue.novel.service.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentReportServiceImpl implements CommentReportService {

    @Autowired
    private CommentReportDao commentReportDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CommentReportDO get(Long id) {
        return commentReportDao.get(id);
    }

    @Override
    public List<CommentReportDO> list(Map<String, Object> map) {
        return commentReportDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return commentReportDao.count(map);
    }

    @Override
    public int update(CommentReportDO commentReport) {
        return commentReportDao.update(commentReport);
    }

    @Override
    public int remove(Long id) {
        return commentReportDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return commentReportDao.batchRemove(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int handleReport(Long id, Byte status, String handlerNote) {
        CommentReportDO report = commentReportDao.get(id);
        if (report == null) {
            return 0;
        }

        // 更新举报记录
        report.setStatus(status);
        report.setHandlerNote(handlerNote);
        report.setUpdateTime(new Date());
        commentReportDao.update(report);

        // 如果采纳举报（status=2），隐藏对应的评论/回复
        if (status == 2) {
            if (report.getTargetType() == 1) {
                jdbcTemplate.update("update book_comment set audit_status = 2 where id = ?", report.getTargetId());
            } else {
                jdbcTemplate.update("update book_comment_reply set audit_status = 2 where id = ?", report.getTargetId());
            }
        }

        return 1;
    }
}