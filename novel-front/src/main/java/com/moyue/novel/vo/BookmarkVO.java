package com.moyue.novel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moyue.novel.entity.UserBookmark;
import lombok.Data;

import java.util.Date;

@Data
public class BookmarkVO extends UserBookmark {

    private String indexName;

    private String bookName;

    private String picUrl;

    private String authorName;

    @JsonFormat(timezone = "GMT+8", pattern = "MM/dd HH:mm")
    private Date createTime;

    @Override
    public String toString() {
        return super.toString();
    }
}
