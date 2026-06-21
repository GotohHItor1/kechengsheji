package com.example.mapper;

import com.example.pojo.Dept;

import java.util.List;

/**
 * 部门 Mapper 接口
 */
public interface DeptMapper {

    /** 查询所有部门 */
    List<Dept> list();

    /** 新增部门 */
    void insert(Dept dept);

    /** 根据ID删除部门 */
    void deleteById(Integer id);

    /** 根据ID查询部门 */
    Dept getById(Integer id);

    /** 修改部门 */
    void update(Dept dept);
}
