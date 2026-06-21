package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学员 Mapper 接口
 */
public interface StudentMapper {

    /**
     * 条件分页查询学员列表
     * @param name    学员姓名 (模糊查询)
     * @param degree  学历
     * @param clazzId 班级ID
     * @return 学员列表
     */
    List<Student> list(@Param("name") String name,
                       @Param("degree") Short degree,
                       @Param("clazzId") Integer clazzId);

    /** 新增学员 */
    void insert(Student student);

    /** 批量删除学员 */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /** 根据ID查询学员 */
    Student getById(Integer id);

    /** 修改学员 */
    void update(Student student);

    /** 学员违纪扣分 */
    void updateViolation(@Param("id") Integer id, @Param("score") Integer score);

    /** 按学历统计学员数量 */
    List<java.util.Map<String, Object>> countByDegree();
}
