package com.moyue.novel.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.xxyopen.model.page.PageBean;
import io.github.xxyopen.web.util.BeanUtil;
import com.moyue.novel.mapper.FrontNewsMapper;
import com.moyue.novel.service.NewsService;
import com.moyue.novel.core.cache.CacheKey;
import com.moyue.novel.core.cache.CacheService;
import com.moyue.novel.entity.News;
import com.moyue.novel.mapper.NewsMapper;
import com.moyue.novel.vo.NewsVO;
import io.github.xxyopen.model.page.builder.pagehelper.PageBuilder;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moyue.novel.mapper.NewsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final FrontNewsMapper newsMapper;

    private final CacheService cacheService;


    @Override
    public List<News> listIndexNews() {
        List<News> result = cacheService.getList(CacheKey.INDEX_NEWS_KEY, News.class);
        if (result == null || result.isEmpty()) {
            SelectStatementProvider selectStatement = select(id, catName, catId, title, createTime)
                .from(news)
                .orderBy(createTime.descending())
                .limit(2)
                .build()
                .render(RenderingStrategies.MYBATIS3);
            result = newsMapper.selectMany(selectStatement);
            cacheService.setObject(CacheKey.INDEX_NEWS_KEY, result, 60 * 60 * 12);
        }
        return result;
    }

    @Override
    public News queryNewsInfo(Long newsId) {
        SelectStatementProvider selectStatement = select(news.allColumns())
            .from(news)
            .where(id, isEqualTo(newsId))
            .build()
            .render(RenderingStrategies.MYBATIS3);
        return newsMapper.selectMany(selectStatement).get(0);

    }

    @Override
    public PageBean<News> listByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        SelectStatementProvider selectStatement = select(id, catName, catId, title, createTime)
            .from(news)
            .orderBy(createTime.descending())
            .build()
            .render(RenderingStrategies.MYBATIS3);
        List<News> news = newsMapper.selectMany(selectStatement);
        PageBean<News> pageBean = PageBuilder.build(news);
        pageBean.setList(BeanUtil.copyList(news, NewsVO.class));
        return pageBean;
    }

    @Override
    public void addReadCount(Integer newsId) {
        newsMapper.addReadCount(newsId);
    }
}
