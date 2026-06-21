package com.example.service;

import com.example.dto.PageBean;
import com.example.pojo.Clazz;

import java.util.List;

/**
 * 班级业务接口
 */
public interface ClazzService {

    /** 条件分页查询班级 */
    PageBean page(String name, String begin, String end, Short status, Integer page, Integer pageSize);

    /** 查询全部班级 */
    List<Clazz> listAll();

    /** 新增班级 */
    void add(Clazz clazz);

    /** 删除班级 */
    void delete(Integer id);

    /** 根据ID查询班级 */
    Clazz getById(Integer id);

    /** 修改班级 */
    void update(Clazz clazz);
}
