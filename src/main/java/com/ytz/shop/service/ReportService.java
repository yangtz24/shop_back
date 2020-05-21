package com.ytz.shop.service;

import com.ytz.shop.vo.ReportVO;

import java.util.List;

/**
 * @ClassName: ReportService
 * @Description: 数据统计业务接口
 * @author: yangtianzeng
 * @date: 2020/5/2 15:15
 */
public interface ReportService {

    /**
     * 查询所有
     * @return
     */
    List<ReportVO> getAll();
}
