package com.ytz.shop.repository.jpa;

import com.ytz.shop.pojo.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ReportRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/5/2 15:14
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {
}
