/*
package com.ytz.shop.es.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

*/
/**
 * @ClassName: EsGoods
 * @Description: TODO  搜索的商品信息
 * @author: basketBoy
 * @date: 2020/6/6
 * @Version: V1.0
 *//*

@Document(indexName = "shop", type = "goods",shards = 1,replicas = 0)
@Getter
@Setter
public class EsGoods implements Serializable {
    private static final long serialVersionUID = 5615738161000775186L;

    @Id
    private Long id;

    private Long goodsCategoryId;

    private Long goodsAttributeId;

    @Field(type = FieldType.Keyword)
    private String goodsAttributeName;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;

    private BigDecimal price;

    private Integer status;

    private Integer amount;

    private BigDecimal weight;

    @Field(type =FieldType.Nested)
    private List<EsGoodsAttributeValue> attributeValueList;

}
*/
