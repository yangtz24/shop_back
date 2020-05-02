package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Report
 * @Description: 数据统计信息
 * @author: yangtianzeng
 * @date: 2020/5/2 15:10
 */
@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
public class Report implements Serializable {
    private static final long serialVersionUID = -6992516807279809143L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("用户数量")
    private Integer userCount;

    @ApiModelProperty("用户分布区域")
    private String area;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
}
