package com.moyue.novel.entity;


import javax.annotation.Generated;
import java.util.Date;

public class BookComment {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long bookId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String commentContent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String location;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer replyCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer likeCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte auditStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createUserId;

    private Long indexId;

    private String paragraphId;

    private Byte commentType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getBookId() {
        return bookId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCommentContent() {
        return commentContent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLocation() {
        return location;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getReplyCount() {
        return replyCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getLikeCount() {
        return likeCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getAuditStatus() {
        return auditStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
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
    public Long getCreateUserId() {
        return createUserId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getParagraphId() {
        return paragraphId;
    }

    public void setParagraphId(String paragraphId) {
        this.paragraphId = paragraphId == null ? null : paragraphId.trim();
    }

    public Byte getCommentType() {
        return commentType;
    }

    public void setCommentType(Byte commentType) {
        this.commentType = commentType;
    }
}