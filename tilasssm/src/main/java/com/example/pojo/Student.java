package com.example.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学员实体类
 * 对应数据库 student 表
 */
public class Student {
    private Integer id;
    private String name;
    /** 学号 */
    private String no;
    /** 性别: 1-男, 2-女 */
    private Short gender;
    private String phone;
    /** 身份证号 */
    private String idCard;
    /** 学历: 1-初中, 2-高中, 3-大专, 4-本科, 5-硕士, 6-博士 */
    private Short degree;
    /** 是否高校毕业: 0-否, 1-是 */
    private Short isCollege;
    /** 联系地址 */
    private String address;
    /** 毕业日期 */
    private LocalDate graduationDate;
    /** 违纪次数 */
    private Integer violationCount;
    /** 违纪扣分 */
    private Integer violationScore;
    private Integer clazzId;
    /** 班级名称 (关联查询) */
    private String clazzName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Student() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }
    public Short getGender() { return gender; }
    public void setGender(Short gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public Short getDegree() { return degree; }
    public void setDegree(Short degree) { this.degree = degree; }
    public Short getIsCollege() { return isCollege; }
    public void setIsCollege(Short isCollege) { this.isCollege = isCollege; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getGraduationDate() { return graduationDate; }
    public void setGraduationDate(LocalDate graduationDate) { this.graduationDate = graduationDate; }
    public Integer getViolationCount() { return violationCount; }
    public void setViolationCount(Integer violationCount) { this.violationCount = violationCount; }
    public Integer getViolationScore() { return violationScore; }
    public void setViolationScore(Integer violationScore) { this.violationScore = violationScore; }
    public Integer getClazzId() { return clazzId; }
    public void setClazzId(Integer clazzId) { this.clazzId = clazzId; }
    public String getClazzName() { return clazzName; }
    public void setClazzName(String clazzName) { this.clazzName = clazzName; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + '\'' + ", no='" + no + '\'' + '}';
    }
}
