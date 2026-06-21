package com.example.service.impl;

import com.example.dto.StudentCountReport;
import com.example.mapper.ReportMapper;
import com.example.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据统计业务实现
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Map<String, Object>> empGenderData() {
        return reportMapper.empGenderData();
    }

    @Override
    public List<Map<String, Object>> empJobData() {
        return reportMapper.empJobData();
    }

    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return reportMapper.studentDegreeData();
    }

    @Override
    public StudentCountReport studentCountData() {
        List<Map<String, Object>> rows = reportMapper.studentCountData();
        List<String> clazzList = new ArrayList<>();
        List<Long> dataList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            clazzList.add((String) row.get("name"));
            dataList.add(((Number) row.get("value")).longValue());
        }
        return new StudentCountReport(clazzList, dataList);
    }
}
