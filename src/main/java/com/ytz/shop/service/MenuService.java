package com.ytz.shop.service;

import com.ytz.shop.pojo.Menu;

import java.util.List;

/**
 * @ClassName: MenuService
 * @Description: 菜单配置信息 业务接口
 * @author: yangtianzeng
 * @date: 2020/4/11 9:40
 */
public interface MenuService {
    /**
     * 查询全部
     * @return
     */
    List<Menu> list();

    /**
     * 根据父类ID查询子菜单
     * @param parentId
     * @return
     */
    List<Menu> getMenuByParentId(Long parentId);

    /**
     * 查询一个，根据ID
     * @param id
     * @return
     */
    Menu getOneById(Long id);
}
