package com.ytz.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ytz.shop.pojo.Goods;
import com.ytz.shop.repository.GoodsRepository;
import com.ytz.shop.service.GoodsService;
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
 * @ClassName: GoodsServiceImpl
 * @Description: 商品业务实现类
 * @author: yangtianzeng
 * @date: 2020/4/28 14:41
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods add(Goods goods) {
        return null;
    }

    @Override
    public int modify(Long id, Goods goods) {
        return 0;
    }

    @Override
    public int remove(Long id) {
        return goodsRepository.updateDeleted(id);
    }

    @Override
    public Page<Goods> list(Integer pageNumber, Integer pageSize, String key) {
        if (pageNumber != null && pageNumber != 0) {
            pageNumber = pageNumber - 1;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Specification<Goods> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(key)) {
                Predicate p = criteriaBuilder.like(root.get("name"), key + "%");
                list.add(p);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<Goods> goodsPageList = goodsRepository.findAll(specification, pageable);
        return goodsPageList;
    }

    @Override
    public Goods getOne(Long id) {
        Optional<Goods> goodsOptional = goodsRepository.findById(id);
        if (ObjectUtil.isNotEmpty(goodsOptional)) {
            Goods goods = goodsOptional.get();
            return goods;
        }
        return null;
    }
}
