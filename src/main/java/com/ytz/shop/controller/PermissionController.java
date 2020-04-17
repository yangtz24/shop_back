package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Permission;
import com.ytz.shop.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: PermissionController
 * @Description: 权限控制层
 * @author: yangtianzeng
 * @date: 2020/4/16 10:14
 */
@Api(tags = "PermissionController", description = "权限Controller")
@RestController
@RequestMapping("rest/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取列表")
    @GetMapping("")
    public CommonResult<Page<Permission>> list(Integer pageNumber, Integer pageSize, String key) {
        Page<Permission> permissions = permissionService.list(pageNumber, pageSize, key);
        if (CollUtil.isNotEmpty(permissions)) {
            return CommonResult.success(permissions);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("获取父级下面所有子级的数据")
    @GetMapping("parent")
    public CommonResult<List<Permission>> getChildren() {
        List<Permission> permissionList = permissionService.getParentAndChildren();
        return CommonResult.success(permissionList);
    }
}
