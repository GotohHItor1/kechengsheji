package com.example.service;

import com.example.dto.StudentCountReport;

import java.util.List;
import java.util.Map;

/**
 * 数据统计业务接口
 */
public interface ReportService {

    /** 员工性别分布统计 */
    List<Map<String, Object>> empGenderData();

    /** 员工职位人数统计 */
    List<Map<String, Object>> empJobData();

    /** 学员学历分布统计 */
    List<Map<String, Object>> studentDegreeData();

    /** 班级人数统计 (返回 {clazzList, dataList} 格式) */
    StudentCountReport studentCountData();
}
