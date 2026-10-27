package com.moyue.novel.controller;

import com.moyue.common.utils.PageBean;
import com.moyue.common.utils.Query;
import com.moyue.common.utils.R;
import com.moyue.novel.domain.CommentReportDO;
import com.moyue.novel.service.CommentReportService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/novel/commentReport")
public class CommentReportController {

    @Autowired
    private CommentReportService commentReportService;

    @GetMapping()
    @RequiresPermissions("novel:commentReport:commentReport")
    String commentReport() {
        return "novel/commentReport/commentReport";
    }

    @ApiOperation(value = "获取举报列表", notes = "获取举报列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:commentReport:commentReport")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<CommentReportDO> commentReportList = commentReportService.list(query);
        int total = commentReportService.count(query);
        PageBean pageBean = new PageBean(commentReportList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "处理举报", notes = "处理举报")
    @PostMapping("/handle")
    @ResponseBody
    @RequiresPermissions("novel:commentReport:handle")
    public R handle(Long id, Byte status, String handlerNote) {
        if (commentReportService.handleReport(id, status, handlerNote) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "删除举报记录", notes = "删除举报记录")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:commentReport:remove")
    public R remove(Long id) {
        if (commentReportService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "批量删除举报记录", notes = "批量删除举报记录")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:commentReport:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        commentReportService.batchRemove(ids);
        return R.ok();
    }
}