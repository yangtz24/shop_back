package com.ytz.shop.es.service;

import com.ytz.shop.es.document.EsGoods;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: EsGoodsService
 * @Description: TODO  商品检索业务接口
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 */
public interface EsGoodsService {

    /**
     * 从数据库中导入所有商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     * @param id
     * @return
     */
    EsGoods create(Long id);

    /**
     * 批量删除商品
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsGoods> search(String keyword, Integer pageNum, Integer pageSize);
}
