package com.ytz.shop.repository;

import com.ytz.shop.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: MenuRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/11 9:39
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByParentId(Long parentId);
}
