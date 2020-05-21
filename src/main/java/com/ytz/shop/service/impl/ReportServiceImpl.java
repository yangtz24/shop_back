package com.ytz.shop.service.impl;

import com.ytz.shop.pojo.Report;
import com.ytz.shop.repository.ReportRepository;
import com.ytz.shop.service.ReportService;
import com.ytz.shop.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @ClassName: ReportServiceImpl
 * @Description: 数据统计业务实现
 * @author: yangtianzeng
 * @date: 2020/5/2 15:16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<ReportVO> getAll() {
        List<Report> all = reportRepository.findAll();
        // 分组
        Map<String, List<Report>> map = all.stream().collect(groupingBy(report -> report.getArea()));
        List<ReportVO> list = new ArrayList<>();
        map.forEach((key, value) -> {
            ReportVO reportVO = new ReportVO();
            reportVO.setArea(key);
            reportVO.setReports(value);
            list.add(reportVO);
        });
        return list;
    }
}
