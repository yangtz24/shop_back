package com.ytz.shop.es.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName: EsGoodsAttributeValue
 * @Description: TODO  商品属性信息
 * @author: basketBoy
 * @date: 2020/6/6
 * @Version: V1.0
 */
@Setter
@Getter
public class EsGoodsAttributeValue implements Serializable {
    private static final long serialVersionUID = -4476580286728981719L;

    private Long id;
    private Long goodsAttributeId;
    /**
     * 属性值
     */
    @Field(type = FieldType.Keyword)
    private String value;
    /**
     * 属性参数
     */
    private Integer type;
    /**
     * 属性名称
     */
    @Field(type=FieldType.Keyword)
    private String name;
}
