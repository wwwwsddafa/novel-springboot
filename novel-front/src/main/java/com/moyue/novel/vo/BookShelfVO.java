package com.moyue.novel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moyue.novel.entity.UserBookshelf;
import lombok.Data;

import java.util.Date;

/**
 */
@Data
public class BookShelfVO extends UserBookshelf {

    private Integer catId;
    private String catName;
    private Long lastIndexId;

    private String lastIndexName;
    private String bookName;


    private String picUrl;

    private Long authorId;

    private String authorName;

    private String bookDesc;

    @JsonFormat(timezone = "GMT+8", pattern = "MM/dd HH:mm")
    private Date lastIndexUpdateTime;

    private Byte bookStatus;


    @Override
    public String toString() {
        return super.toString();
    }
}
