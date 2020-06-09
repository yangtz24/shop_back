package com.ytz.shop.repository.jpa;

//import com.ytz.shop.es.document.EsGoods;
import com.ytz.shop.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: GoodsRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/28 14:35
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE goods SET deleted = 1 WHERE id = ?1")
    int updateDeleted(Long id);

    /**
     * 查询商品列表表，根据ID
     * @param id
     * @return
     */
    /*@Query(nativeQuery = true, value = "SELECT\n" +
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
            "        WHERE g.status = 2 AND if(?1 ='' OR ?1 IS NULL, 1=1, id = ?1)")*/
//    List<EsGoods> findAllEsGoodsList(Long id);
}
