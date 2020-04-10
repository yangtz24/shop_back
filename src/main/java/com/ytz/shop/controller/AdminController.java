package com.ytz.shop.controller;

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
}
