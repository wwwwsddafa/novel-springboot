package com.moyue.novel.core.crawl;

import com.moyue.novel.entity.BookContent;
import com.moyue.novel.entity.BookIndex;
import lombok.Data;

import java.util.List;

/**
 * 章节数据封装bean
 * @author Administrator
 */
@Data
public class ChapterBean {

    /**
     * 章节索引集合
     * */
    List<BookIndex> bookIndexList;

    /**
     * 章节内容集合
     * */
    List<BookContent> bookContentList;
}
