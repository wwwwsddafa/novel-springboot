package com.moyue.novel.core.crawl;

import com.moyue.novel.entity.Book;

/**
 * 爬虫小说章节内容处理器
 * */
public interface CrawlBookChapterHandler {

    void handle(ChapterBean chapterBean);

}
