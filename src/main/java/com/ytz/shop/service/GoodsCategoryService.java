package com.ytz.shop.service;

import com.ytz.shop.pojo.GoodsCategory;
import org.springframework.data.domain.Page;

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
}
