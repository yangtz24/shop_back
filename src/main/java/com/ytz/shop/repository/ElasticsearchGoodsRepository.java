package com.ytz.shop.repository;

import com.ytz.shop.es.document.EsGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EsGoodsRepository
 * @Description: TODO
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 */
@Repository
public interface ElasticsearchGoodsRepository extends JpaRepository<EsGoods, Long> {

    /**
     * 查询商品列表表，根据ID
     * @param id
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT\n" +
            "        g.id id,\n" +
            "        g.cate_id goodsCategoryId,\n" +
            "        g.name name,\n" +
            "        g.sub_title subTitle,\n" +
            "        g.price price,\n" +
            "        g.amount amount,\n" +
            "        g.weight weight,\n" +
            "        g.keywords keywords,\n" +
            "        a.id goodsAttributeId,\n" +
            "        a.vals attr_value,\n" +
            "        a.type attr_type,\n" +
            "        a.name goodsAttributeName\n" +
            "        FROM goods g\n" +
            "        LEFT JOIN goods_attr ga ON ga.goods_id= g.id\n" +
            "        LEFT JOIN attribute a ON ga.attr_id= ga.id\n" +
            "        WHERE g.status = 2 AND if(?1 ='' OR ?1 IS NULL, 1=1, id = ?1)")
    List<EsGoods> findAllEsGoodsList(Long id);
}
