package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.pojo.GoodsCategory;
import com.ytz.shop.repository.GoodsCategoryRepository;
import com.ytz.shop.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: GoodsCategoryServiceImpl
 * @Description: 商品分类 业务实现类
 * @author: yangtianzeng
 * @date: 2020/4/17 17:53
 */
@Service
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository categoryRepository;


    @Override
    public Page<GoodsCategory> list(Integer currentPage, Integer pageSize, String key) {
        if (currentPage != null && currentPage != 0) {
            currentPage = currentPage - 1;
        }
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Specification<GoodsCategory> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(key)) {
                Predicate p = criteriaBuilder.like(root.get("name"), key + "%");
                list.add(p);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<GoodsCategory> categories = categoryRepository.findAll(specification, pageable);
        return categories;
    }

    @Override
    public List<GoodsCategory> cascadeList() {
        final List<GoodsCategory> categoryList = categoryRepository.findByParentId(0L);
        return categoryList;

    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public GoodsCategory add(GoodsCategory category) {
        category.setDeleted(GoodsCategory.DELETED_NO);
        GoodsCategory goodsCategory = categoryRepository.save(category);
        return goodsCategory;
    }

    @Override
    public GoodsCategory edit(Long id, GoodsCategory goodsCategory) {
        goodsCategory.setId(id);
        GoodsCategory category = categoryRepository.saveAndFlush(goodsCategory);
        return category;
    }

    @Override
    public GoodsCategory detail(Long id) {
        Optional<GoodsCategory> categoryOptional = categoryRepository.findById(id);
        GoodsCategory category = categoryOptional.get();
        return category;
    }

    @Override
    public List<GoodsCategory> all() {
        List<GoodsCategory> all = categoryRepository.findAll();
        return all;
    }
}
