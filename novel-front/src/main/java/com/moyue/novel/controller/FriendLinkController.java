package com.moyue.novel.controller;


import com.moyue.novel.entity.FriendLink;
import com.moyue.novel.service.FriendLinkService;
import io.github.xxyopen.model.resp.RestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RequestMapping("friendLink")
@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    /**
     * 查询首页友情链接
     * */
    @GetMapping("listIndexLink")
    public RestResult<List<FriendLink>> listIndexLink(){
        return RestResult.ok(friendLinkService.listIndexLink());
    }




}
