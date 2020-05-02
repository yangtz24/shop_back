package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Report;
import com.ytz.shop.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: ReportController
 * @Description: 数据统计Controller
 * @author: yangtianzeng
 * @date: 2020/5/2 15:18
 */
@Api(tags = "ReportController", description = "数据统计Controller")
@RestController
@RequestMapping("rest/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation("查询全部")
    @GetMapping("")
    public CommonResult<List<Report>> all() {
        List<Report> reports = reportService.getAll();
        if (CollUtil.isNotEmpty(reports)) {
            return CommonResult.success(reports);
        }
        return CommonResult.failed("查询失败");
    }
}
