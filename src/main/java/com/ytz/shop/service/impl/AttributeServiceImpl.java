package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.common.enums.AttributeType;
import com.ytz.shop.pojo.Attribute;
import com.ytz.shop.repository.jpa.AttributeRepository;
import com.ytz.shop.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: AttributeServiceImpl
 * @Description: 分类参数 业务实现
 * @author: yangtianzeng
 * @date: 2020/4/23 16:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public List<Attribute> getByCategoryId(Long cateId, String type) {
        if (StrUtil.isBlank(type)) {
            type = AttributeType.only.toString();
        }

        AttributeType attributeType = setType(type);
        List<Attribute> attributes = attributeRepository.findByCateIdAndDeletedAndType(cateId, Attribute.UN_DELETED, attributeType);
        return attributes;
    }

    // 转化数据类型
    private AttributeType setType(String type) {
        AttributeType attrType = null;
        for (AttributeType attributeType : AttributeType.values()) {
            if (type.equals(attributeType.toString())) {
                attrType = attributeType;
            }
        }
        return attrType;
    }

    @Override
    public Attribute add(Long cateId, Attribute attr) {
        attr.setCateId(cateId);
        Attribute attribute = attributeRepository.save(attr);
        return attribute;
    }

    @Override
    public Integer edit(Long cateId, Long id, Attribute attr) {
        attr.setCateId(cateId);
        attr.setId(id);
        Integer result = attributeRepository.update(attr);
        return result;
    }

    @Override
    public Attribute detail(Long cateId, Long id, String type) {
        if (StrUtil.isBlank(type)) {
            type = AttributeType.only.toString();
        }
        AttributeType attributeType = setType(type);
        Attribute attribute = attributeRepository.findByCateIdAndIdAndType(cateId, id, attributeType);
        return attribute;
    }

    @Override
    public Integer remove(Long cateId, Long id) {
        int result = attributeRepository.delete(cateId, id);
        return result;
    }
}
