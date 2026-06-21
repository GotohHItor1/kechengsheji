package com.example.dto;

import java.util.List;

/**
 * 班级人数统计 DTO
 * 响应格式: { clazzList: [...], dataList: [...] }
 */
public class StudentCountReport {
    private List<String> clazzList;
    private List<Long> dataList;

    public StudentCountReport() {}

    public StudentCountReport(List<String> clazzList, List<Long> dataList) {
        this.clazzList = clazzList;
        this.dataList = dataList;
    }

    public List<String> getClazzList() { return clazzList; }
    public void setClazzList(List<String> clazzList) { this.clazzList = clazzList; }
    public List<Long> getDataList() { return dataList; }
    public void setDataList(List<Long> dataList) { this.dataList = dataList; }
}
