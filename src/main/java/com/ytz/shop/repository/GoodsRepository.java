package com.ytz.shop.repository;

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
}
