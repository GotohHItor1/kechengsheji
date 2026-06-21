package com.example.pojo;

import java.time.LocalDateTime;

/**
 * 操作日志实体类
 */
public class OperateLog {
    private Integer id;
    private Integer operateEmp;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    private Long costTime;

    public OperateLog() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getOperateEmp() { return operateEmp; }
    public void setOperateEmp(Integer operateEmp) { this.operateEmp = operateEmp; }
    public LocalDateTime getOperateTime() { return operateTime; }
    public void setOperateTime(LocalDateTime operateTime) { this.operateTime = operateTime; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
    public String getMethodParams() { return methodParams; }
    public void setMethodParams(String methodParams) { this.methodParams = methodParams; }
    public String getReturnValue() { return returnValue; }
    public void setReturnValue(String returnValue) { this.returnValue = returnValue; }
    public Long getCostTime() { return costTime; }
    public void setCostTime(Long costTime) { this.costTime = costTime; }

    @Override
    public String toString() {
        return "OperateLog{id=" + id + ", method='" + methodName + '\'' + '}';
    }
}
