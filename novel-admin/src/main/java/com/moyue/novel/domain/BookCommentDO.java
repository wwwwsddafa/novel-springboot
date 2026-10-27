package com.moyue.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moyue.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 小说评论表
 *
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2023-04-14 21:59:28
 */
public class BookCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //主键
    //java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
    //所以通过序列化成字符串来解决
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //小说ID
    //java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
    //所以通过序列化成字符串来解决
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long bookId;
    //评价内容
    private String commentContent;
    //回复数量
    private Integer replyCount;
    //点赞数量
    private Integer likeCount;
    //审核状态，0：待审核，1：审核通过，2：审核不通过
    private Integer auditStatus;
    //评价时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //评价人
    //java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
    //所以通过序列化成字符串来解决
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long createUserId;

    private String bookName;

    private String userName;

    private Integer reportCount;
    //章节ID
    private Long indexId;
    //段落ID
    private String paragraphId;

    private Byte commentType;

    private String indexType;

    private String indexName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
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
        this.paragraphId = paragraphId;
    }

    public Byte getCommentType() {
        return commentType;
    }

    public void setCommentType(Byte commentType) {
        this.commentType = commentType;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：小说ID
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取：小说ID
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置：评价内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取：评价内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置：回复数量
     */
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 获取：回复数量
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 设置：点赞数量
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取：点赞数量
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置：审核状态，0：待审核，1：审核通过，2：审核不通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取：审核状态，0：待审核，1：审核通过，2：审核不通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置：评价时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：评价时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：评价人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：评价人
     */
    public Long getCreateUserId() {
        return createUserId;
    }
}