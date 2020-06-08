package com.ytz.shop.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.pojo.Menu;
import com.ytz.shop.repository.jpa.MenuRepository;
import com.ytz.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单配置信息 业务实现
 * @author: yangtianzeng
 * @date: 2020/4/11 9:43
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> list() {
        List<Menu> menus = menuRepository.findAll();
        menus = sortByQueue(menus);
        //获取一级菜单
        List<Menu> firstMenus = menus.stream()
                .filter(m -> Menu.ONE_MENU.equals(m.getParentId()))
                .collect(Collectors.toList());
        //二级菜单
        List<Menu> secMenus = menus.stream()
                .filter(m -> !Menu.ONE_MENU.equals(m.getParentId()))
                .collect(Collectors.toList());
        for (Menu firstMenu : firstMenus) {
            List<Menu> list = new ArrayList<>();
            for (Menu secMenu : secMenus) {
                if (secMenu.getParentId().equals(firstMenu.getId())) {
                    list.add(secMenu);
                }
            }
            firstMenu.setChildren(list);
        }
        return firstMenus;
    }

    @Override
    public List<Menu> getMenuByParentId(Long parentId) {
        List<Menu> menuList = menuRepository.findAllByParentId(parentId);
        menuList = sortByQueue(menuList);
        return menuList;
    }

    @Override
    public Menu getOneById(Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            return optionalMenu.get();
        } else {
            return null;
        }
    }

    /**
     * 根据顺序排序
     * @param menus
     * @return
     */
    private List<Menu> sortByQueue(List<Menu> menus) {
        if (CollUtil.isEmpty(menus)) {
            return null;
        }
        return menus.stream().sorted((a, b) -> {
            if (a.getQueue() != b.getQueue()) {
                return a.getQueue() - b.getQueue();
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }
}
