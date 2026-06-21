package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

/**
 * 部门业务接口
 */
public interface DeptService {

    /** 查询所有部门 */
    List<Dept> list();

    /** 新增部门 */
    void add(Dept dept);

    /** 删除部门 */
    void delete(Integer id);

    /** 根据ID查询部门 */
    Dept getById(Integer id);

    /** 修改部门 */
    void update(Dept dept);
}
