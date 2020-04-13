package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.dto.AdminLoginParam;
import com.ytz.shop.pojo.Permission;
import com.ytz.shop.pojo.UserAdmin;
import com.ytz.shop.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AdminController
 * @Description: 后台用户 Controller
 * @author: yangtianzeng
 * @date: 2020/4/8 14:26
 */
@Api(tags = "AdminController", description = "用户管理")
@RestController
@RequestMapping("rest/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("注册")
    @PostMapping("register")
    public CommonResult<UserAdmin> register(@RequestBody UserAdmin userAdmin, BindingResult result) {
        UserAdmin admin = adminService.register(userAdmin);
        if(admin == null) {
            CommonResult.failed("注册失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation("登录成功后，返回token信息")
    @PostMapping("login")
    public CommonResult login(@RequestBody AdminLoginParam AdminLoginParam, BindingResult result) {
        String token = adminService.login(AdminLoginParam.getUsername(), AdminLoginParam.getPassword());
        if(StringUtils.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation("获取用户所有权限")
    @GetMapping("permission/{adminId}")
    public CommonResult<List<Permission>> getPermissionList(@PathVariable Long adminId) {
        List<Permission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation("更新用户状态")
    @PutMapping("{id}/status/{status}")
    public CommonResult<Integer> modifyStatus(@PathVariable Long id, @PathVariable Integer status) {
        int result = adminService.modifyStatus(id, status);
        if (result == 1) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("更新状态失败");
    }

    @ApiOperation("添加管理员用户")
    @PostMapping("")
    public CommonResult<UserAdmin> add(@RequestBody UserAdmin admin) {
        UserAdmin userAdmin = adminService.add(admin);
        if(ObjectUtil.isEmpty(userAdmin)) {
            CommonResult.failed("添加失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation("分页查询")
    @GetMapping("page")
    public CommonResult<Page<UserAdmin>> list(@RequestParam("pageNumber") Integer pageNum, Integer pageSize, @RequestParam("query") String key, String phone, Integer status) {
        Page<UserAdmin> page = adminService.list(pageNum, pageSize, key, phone, status);
        if (CollUtil.isNotEmpty(page)) {
            return CommonResult.success(page);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("获取单个用户详情")
    @GetMapping("{id}")
    public CommonResult<UserAdmin> getOne(@PathVariable Long id) {
        UserAdmin userAdmin = adminService.detail(id);
        if (ObjectUtil.isNotEmpty(userAdmin)) {
            return CommonResult.success(userAdmin);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("更新用户信息")
    @PutMapping("modify/{id}")
    public CommonResult<Integer> modify(@RequestBody UserAdmin userAdmin, @PathVariable Long id) {
        int result = adminService.edit(userAdmin, id);
        if (result == 1) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("更新操作失败!!!");
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable Long id) {
        adminService.remove(id);
        return CommonResult.success(1, "删除操作成功！！！");
    }


}
