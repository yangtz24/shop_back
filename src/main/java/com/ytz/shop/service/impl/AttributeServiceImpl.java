package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.common.enums.AttributeType;
import com.ytz.shop.pojo.Attribute;
import com.ytz.shop.repository.AttributeRepository;
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

        AttributeType attributeType = null;
        for (AttributeType attributeType1 : AttributeType.values()) {
            if (type.equals(attributeType1.toString())) {
                attributeType = attributeType1;
            }
        }
        List<Attribute> attributes = attributeRepository.findByCateIdAndType(cateId, attributeType);
        return attributes;
    }
}
