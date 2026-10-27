package com.moyue.novel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserBookmark {
    private Long id;

    private Long userId;

    private Long bookId;

    private Long indexId;

    private String bookmarkName;

    private Integer position;

    private Date createTime;
}