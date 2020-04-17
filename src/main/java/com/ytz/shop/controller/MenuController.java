package com.ytz.shop.controller;

import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Menu;
import com.ytz.shop.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: MenuController
 * @Description: 菜单Controller
 * @author: yangtianzeng
 * @date: 2020/4/11 9:59
 */
@Api(tags = "MenuController", description = "菜单配置信息Controller")
@RestController
@RequestMapping("rest/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("菜单列表")
    @GetMapping("")
    public CommonResult<List<Menu>> list() {
        List<Menu> list = menuService.list();
        return CommonResult.success(list);
    }

    @ApiOperation("获取二级菜单列表根据一级菜单ID")
    @GetMapping("{id}")
    public CommonResult<List<Menu>> childrenMenus(@PathVariable("id") Long parentId) {
        List<Menu> menuList = menuService.getMenuByParentId(parentId);
        return CommonResult.success(menuList);
    }

    @ApiOperation("获取菜单详情")
    @GetMapping("{id}/detail")
    public CommonResult<Menu> getOne(@PathVariable Long id) {
        Menu menu = menuService.getOneById(id);
        return CommonResult.success(menu);
    }
}
