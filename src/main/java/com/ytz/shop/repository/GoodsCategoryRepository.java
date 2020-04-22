package com.ytz.shop.repository;

import com.ytz.shop.pojo.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GoodsCategoryRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/17 17:50
 */
@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long>, JpaSpecificationExecutor<GoodsCategory> {

    /**
     * 查询等级小于 2 的商品分类
     * @param level
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM GOODS_CATEGORY WHERE LEVEL < ?1")
    List<GoodsCategory> findByLevel(Integer level);

    /**
     * 根据父ID查询
     * @param pId
     * @return
     */
    List<GoodsCategory> findByParentId(Long pId);
}
