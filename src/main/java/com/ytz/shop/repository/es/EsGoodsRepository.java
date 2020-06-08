package com.ytz.shop.repository.es;

import com.ytz.shop.es.document.EsGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: EsGoodsRepository
 * @Description: TODO  商品ES操作类
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 */
@Repository
public interface EsGoodsRepository extends ElasticsearchRepository<EsGoods, Long> {

    /**
     * 根据商品名称 标题 关键字查询
     * @param name  商品名称
     * @param subTitle  商品标题
     * @param keywords  关键字查询
     * @param page  分页
     * @return
     */
    Page<EsGoods> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
