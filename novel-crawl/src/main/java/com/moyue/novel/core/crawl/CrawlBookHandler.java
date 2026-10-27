package com.moyue.novel.core.crawl;

import com.moyue.novel.entity.Book;

/**
 * 爬虫小说处理器
 * */
public interface CrawlBookHandler {

    void handle(Book book) throws InterruptedException;

}
