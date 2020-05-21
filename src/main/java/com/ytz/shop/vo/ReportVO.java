package com.ytz.shop.vo;

import com.ytz.shop.pojo.Report;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName: ReportVO
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/5/21 17:57
 */
@Setter
@Getter
public class ReportVO {

    private String area;
    private List<Report> reports;
}
