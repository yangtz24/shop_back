package com.ytz.shop.service;

import com.ytz.shop.pojo.Goods;
import org.springframework.data.domain.Page;

/**
 * @ClassName: GoodsService
 * @Description: 商品业务处理 接口定义
 * @author: yangtianzeng
 * @date: 2020/4/28 14:37
 */
public interface GoodsService {

    /**
     * 添加商品
     * @param goods
     * @return
     */
    Goods add(Goods goods);

    /**
     * 修改商品
     * @param id
     * @param goods
     * @return
     */
    int modify(Long id, Goods goods);

    /**
     * 删除商品
     * @param id
     */
    int remove(Long id);

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param key
     * @return
     */
    Page<Goods> list(Integer pageNumber, Integer pageSize, String key);

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    Goods getOne(Long id);
}
