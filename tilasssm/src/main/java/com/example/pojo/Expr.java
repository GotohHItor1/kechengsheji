package com.example.pojo;

import java.time.LocalDate;

/**
 * 员工工作经历实体类
 * 注意: 数据库中 begin/end 是 MySQL 保留字，需用反引号包裹
 */
public class Expr {
    private Integer id;
    private Integer empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;

    public Expr() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getEmpId() { return empId; }
    public void setEmpId(Integer empId) { this.empId = empId; }
    public LocalDate getBegin() { return begin; }
    public void setBegin(LocalDate begin) { this.begin = begin; }
    public LocalDate getEnd() { return end; }
    public void setEnd(LocalDate end) { this.end = end; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    @Override
    public String toString() {
        return "Expr{id=" + id + ", company='" + company + '\'' + '}';
    }
}
