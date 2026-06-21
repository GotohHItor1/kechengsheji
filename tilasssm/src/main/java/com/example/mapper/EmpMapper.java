package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 Mapper 接口
 */
public interface EmpMapper {

    /**
     * 条件分页查询员工列表
     * @param name     员工姓名 (模糊查询)
     * @param gender   性别
     * @param begin    入职日期起始
     * @param end      入职日期截止
     * @return 员工列表
     */
    List<Emp> list(@Param("name") String name,
                   @Param("gender") Short gender,
                   @Param("begin") String begin,
                   @Param("end") String end);

    /** 查询全部员工 */
    List<Emp> listAll();

    /** 新增员工 */
    void insert(Emp emp);

    /** 批量删除员工 */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /** 根据ID查询员工 (含工作经历) */
    Emp getById(Integer id);

    /** 修改员工 */
    void update(Emp emp);

    /** 根据用户名查询员工 (登录用) */
    Emp getByUsername(String username);

    /** 按性别统计员工数量 */
    List<java.util.Map<String, Object>> countByGender();

    /** 按职位统计员工数量 */
    List<java.util.Map<String, Object>> countByJob();
}
