package com.ytz.shop.service;

import com.ytz.shop.pojo.GoodsCategory;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: GoodsCategoryService
 * @Description: 商品分类 业务接口
 * @author: yangtianzeng
 * @date: 2020/4/17 17:52
 */
public interface GoodsCategoryService {

    /**
     * 分布查询
     * @param currentPage
     * @param pageSize
     * @param key
     * @return
     */
    Page<GoodsCategory> list(Integer currentPage, Integer pageSize, String key);

    /**
     * 级联查询
     * @return
     */
    List<GoodsCategory> cascadeList();

    /**
     * 删除
     * @param id
     */
    void remove(Long id);

    /**
     * 添加分类
     * @param category
     * @return
     */
    GoodsCategory add(GoodsCategory category);

    /**
     * 修改分类信息
     * @param id
     * @param goodsCategory
     * @return
     */
    GoodsCategory edit(Long id, GoodsCategory goodsCategory);

    /**
     * 查询祥情
     * @param id
     * @return
     */
    GoodsCategory detail(Long id);

    /**
     * 查询全部
     * @return
     */
    List<GoodsCategory> all();
}
