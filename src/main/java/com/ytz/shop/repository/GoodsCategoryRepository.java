package com.ytz.shop.repository;

import com.ytz.shop.pojo.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: GoodsCategoryRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/17 17:50
 */
@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long>, JpaSpecificationExecutor<GoodsCategory> {
}
