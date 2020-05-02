package com.ytz.shop.service.impl;

import com.ytz.shop.pojo.Report;
import com.ytz.shop.repository.ReportRepository;
import com.ytz.shop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Report> getAll() {
        return reportRepository.findAll();
    }
}
