package com.moyue.novel.service.impl;

import com.moyue.novel.service.FriendLinkService;
import com.moyue.novel.core.cache.CacheKey;
import com.moyue.novel.core.cache.CacheService;
import com.moyue.novel.entity.FriendLink;
import com.moyue.novel.mapper.FriendLinkMapper;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moyue.novel.mapper.FriendLinkDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 */
@Service
@RequiredArgsConstructor
public class FriendLinkServiceImpl implements FriendLinkService {

    private final FriendLinkMapper friendLinkMapper;

    private final CacheService cacheService;


    @Override
    public List<FriendLink> listIndexLink() {
        List<FriendLink> result = cacheService.getList(CacheKey.INDEX_LINK_KEY, FriendLink.class);
        if (result == null || result.isEmpty()) {
            SelectStatementProvider selectStatement = select(linkName, linkUrl)
                .from(friendLink)
                .where(isOpen, isEqualTo((byte) 1))
                .orderBy(sort)
                .build()
                .render(RenderingStrategies.MYBATIS3);
            result = friendLinkMapper.selectMany(selectStatement);
            cacheService.setObject(CacheKey.INDEX_LINK_KEY, result, 60 * 60 * 24);
        }
        return result;
    }
}
