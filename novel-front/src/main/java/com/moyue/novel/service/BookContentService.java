package com.moyue.novel.service;

import com.moyue.novel.entity.BookContent;

import java.util.List;

public interface BookContentService {

    BookContent queryBookContent(Long bookId, Long bookIndexId);

}
