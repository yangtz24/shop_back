package com.ytz.shop.service;

import com.ytz.shop.pojo.Attribute;

import java.util.List;

/**
 * @ClassName: AttributeService
 * @Description: 分类参数业务接口
 * @author: yangtianzeng
 * @date: 2020/4/22 17:48
 */
public interface AttributeService {

    /**
     * 根据分类ID查询参数信息
     * @param cateId   分类参数ID
     * @param type   类型 many only
     * @return
     */
    List<Attribute> getByCategoryId(Long cateId, String type);
}
