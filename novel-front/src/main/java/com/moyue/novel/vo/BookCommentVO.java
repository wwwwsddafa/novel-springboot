package com.moyue.novel.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moyue.novel.core.serialize.CommentUserNameSerialize;
import com.moyue.novel.core.serialize.TimeAgoFormatSerialize;
import com.moyue.novel.core.utils.DateUtil;
import com.moyue.novel.entity.BookComment;
import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class BookCommentVO extends BookComment {

    @JsonSerialize(using = CommentUserNameSerialize.class)
    private String createUserName;

    private String createUserPhoto;

    @JsonSerialize(using = TimeAgoFormatSerialize.class)
    private Date createTime;

    private Long likesCount;

    private Long unLikesCount;

    public String getCreateTimeFormat() {
        return DateUtil.formatTimeAgo(getCreateTime());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
