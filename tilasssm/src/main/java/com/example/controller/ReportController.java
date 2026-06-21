package com.example.controller;

import com.example.dto.Result;
import com.example.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计控制器
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    /** GET /report/empGenderData - 员工性别分布统计 */
    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("查询员工性别分布统计");
        return Result.success(reportService.empGenderData());
    }

    /** GET /report/empJobData - 员工职位人数统计 */
    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("查询员工职位人数统计");
        return Result.success(reportService.empJobData());
    }

    /** GET /report/studentDegreeData - 学员学历分布统计 */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("查询学员学历分布统计");
        return Result.success(reportService.studentDegreeData());
    }

    /** GET /report/studentCountData - 班级人数统计 */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("查询班级人数统计");
        return Result.success(reportService.studentCountData());
    }
}
