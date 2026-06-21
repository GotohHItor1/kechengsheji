package com.example.service;

import com.example.dto.PageBean;
import com.example.pojo.Emp;

import java.util.List;

/**
 * 员工业务接口
 */
public interface EmpService {

    /** 条件分页查询员工 */
    PageBean page(String name, Short gender, String begin, String end, Integer page, Integer pageSize);

    /** 查询全部员工 */
    List<Emp> listAll();

    /** 新增员工 (含工作经历) */
    void add(Emp emp);

    /** 批量删除员工 */
    void delete(List<Integer> ids);

    /** 根据ID查询员工 */
    Emp getById(Integer id);

    /** 修改员工 */
    void update(Emp emp);

    /** 员工登录 */
    Emp login(String username, String password);
}
