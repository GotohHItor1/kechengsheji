package com.example.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工实体类
 */
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;      // 1-男, 2-女
    private String phone;
    private Short job;         // 1-班主任, 2-讲师, 3-学工主管, 4-教研主管
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private String deptName;   // 关联查询: 部门名称
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /** 员工工作经历列表 */
    private List<Expr> exprList;

    public Emp() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Short getGender() { return gender; }
    public void setGender(Short gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Short getJob() { return job; }
    public void setJob(Short job) { this.job = job; }
    public Integer getSalary() { return salary; }
    public void setSalary(Integer salary) { this.salary = salary; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
    public List<Expr> getExprList() { return exprList; }
    public void setExprList(List<Expr> exprList) { this.exprList = exprList; }

    @Override
    public String toString() {
        return "Emp{id=" + id + ", name='" + name + '\'' + '}';
    }
}
