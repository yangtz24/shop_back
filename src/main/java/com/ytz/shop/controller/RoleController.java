package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Role;
import com.ytz.shop.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RoleController
 * @Description: 角色控制层
 * @author: yangtianzeng
 * @date: 2020/4/14 15:21
 */
@Api(tags = "RoleController", description = "角色控制层")
@RestController
@RequestMapping("rest/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("分页查询")
    @GetMapping("")
    public CommonResult<Page<Role>> list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize, String key) {
        Page<Role> roles = roleService.list(currentPage, pageSize, key);
        if (CollUtil.isNotEmpty(roles)) {
            return CommonResult.success(roles);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("更新角色状态")
    @PutMapping("{id}/status/{status}")
    public CommonResult<Integer> modifyStatus(@PathVariable Long id, @PathVariable Integer status) {
        int result = roleService.modifyStatus(id, status);
        if (result == 1) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("更新状态失败");
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable Long id) {
        roleService.remove(id);
        return CommonResult.success(1, "删除操作成功！！！");
    }

    @ApiOperation("查询角色下的权限信息")
    @GetMapping("{roleId}/permissions")
    public CommonResult<Role> getOneRolePermissions(@PathVariable Long roleId) {
        Role role = roleService.getPermissionsByRoleId(roleId);
        if (ObjectUtil.isNotEmpty(role)) {
            return CommonResult.success(role);
        }
        return CommonResult.failed("查询失败");
    }
}
