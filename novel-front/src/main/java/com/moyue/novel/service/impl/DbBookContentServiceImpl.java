package com.moyue.novel.service.impl;

import com.moyue.novel.entity.BookContent;
import com.moyue.novel.mapper.BookContentDynamicSqlSupport;
import com.moyue.novel.mapper.BookContentMapper;
import com.moyue.novel.service.BookContentService;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import static com.moyue.novel.mapper.BookContentDynamicSqlSupport.bookContent;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;


@Service(value = "db")
@RequiredArgsConstructor
public class DbBookContentServiceImpl implements BookContentService {

    private final BookContentMapper bookContentMapper;

    @Override
    public BookContent queryBookContent(Long bookId, Long bookIndexId) {
        SelectStatementProvider selectStatement = select(BookContentDynamicSqlSupport.id, BookContentDynamicSqlSupport.content)
                .from(bookContent)
                .where(BookContentDynamicSqlSupport.indexId, isEqualTo(bookIndexId))
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookContentMapper.selectMany(selectStatement).get(0);
    }
}
