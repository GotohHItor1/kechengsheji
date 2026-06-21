package com.example.service;

import com.example.dto.PageBean;
import com.example.pojo.Student;

import java.util.List;

/**
 * 学员业务接口
 */
public interface StudentService {

    /** 条件分页查询学员 */
    PageBean page(String name, Short degree, Integer clazzId, Integer page, Integer pageSize);

    /** 新增学员 */
    void add(Student student);

    /** 批量删除学员 */
    void delete(List<Integer> ids);

    /** 根据ID查询学员 */
    Student getById(Integer id);

    /** 修改学员 */
    void update(Student student);

    /** 学员违纪扣分 */
    void violation(Integer id, Integer score);
}
