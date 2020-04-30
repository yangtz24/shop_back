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

    /**
     * 添加参数
     * @param cateId
     * @param attr
     * @return
     */
    Attribute add(Long cateId, Attribute attr);

    /**
     * 修改参数数据
     * @param cateId
     * @param id
     * @param attr
     * @return
     */
    Integer edit(Long cateId, Long id, Attribute attr);

    /**
     * 查询参数详情，根据参数类型、分类ID和ID
     * @param cateId
     * @param id
     * @param type
     * @return
     */
    Attribute detail(Long cateId, Long id, String type);

    /**
     * 删除属性信息
     * @param cateId
     * @param id
     * @return
     */
    Integer remove(Long cateId, Long id);
}
