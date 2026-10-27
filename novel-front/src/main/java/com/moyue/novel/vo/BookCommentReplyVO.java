package com.moyue.novel.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moyue.novel.core.serialize.CommentUserNameSerialize;
import com.moyue.novel.core.serialize.TimeAgoFormatSerialize;
import com.moyue.novel.entity.BookCommentReply;
import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class BookCommentReplyVO extends BookCommentReply {

    @JsonSerialize(using = CommentUserNameSerialize.class)
    private String createUserName;

    private String createUserPhoto;

    @JsonSerialize(using = TimeAgoFormatSerialize.class)
    private Date createTime;

    private Long likesCount;

    private Long unLikesCount;

    @Override
    public String toString() {
        return super.toString();
    }
}
