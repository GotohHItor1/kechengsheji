package com.example.mapper;

import com.example.pojo.OperateLog;

import java.util.List;

/**
 * 操作日志 Mapper 接口
 */
public interface LogMapper {

    /** 新增操作日志 */
    void insert(OperateLog log);

    /** 分页查询操作日志 */
    List<OperateLog> page();
}
