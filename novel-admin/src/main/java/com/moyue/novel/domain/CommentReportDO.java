package com.moyue.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moyue.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论举报表
 */
public class CommentReportDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;

    private Byte targetType;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long targetId;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long reporterId;

    private String reportReason;

    private String reportType;

    private Byte status;

    private String handlerNote;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 关联查询字段
    private String targetContent;

    private String reporterName;

    private String commenterName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getTargetType() {
        return targetType;
    }

    public void setTargetType(Byte targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getHandlerNote() {
        return handlerNote;
    }

    public void setHandlerNote(String handlerNote) {
        this.handlerNote = handlerNote;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }
}