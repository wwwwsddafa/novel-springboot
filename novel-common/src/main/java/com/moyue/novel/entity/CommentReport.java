package com.moyue.novel.entity;

import java.util.Date;
import javax.annotation.Generated;

public class CommentReport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte targetType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long targetId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long reporterId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String reportReason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String reportType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String handlerNote;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getTargetType() {
        return targetType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTargetType(Byte targetType) {
        this.targetType = targetType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getTargetId() {
        return targetId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getReporterId() {
        return reporterId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getReportReason() {
        return reportReason;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReportReason(String reportReason) {
        this.reportReason = reportReason == null ? null : reportReason.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getReportType() {
        return reportType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getStatus() {
        return status;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getHandlerNote() {
        return handlerNote;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setHandlerNote(String handlerNote) {
        this.handlerNote = handlerNote == null ? null : handlerNote.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}