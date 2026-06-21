package com.example.mapper;

import com.example.pojo.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级 Mapper 接口
 */
public interface ClazzMapper {

    /**
     * 条件分页查询班级列表
     * @param name   班级名称 (模糊查询)
     * @param begin  开课时间起始
     * @param end    开课时间截止
     * @param status 状态
     * @return 班级列表
     */
    List<Clazz> list(@Param("name") String name,
                     @Param("begin") String begin,
                     @Param("end") String end,
                     @Param("status") Short status);

    /** 查询全部班级 */
    List<Clazz> listAll();

    /** 新增班级 */
    void insert(Clazz clazz);

    /** 删除班级 */
    void deleteById(Integer id);

    /** 根据ID查询班级 */
    Clazz getById(Integer id);

    /** 修改班级 */
    void update(Clazz clazz);

    /** 统计各班级人数 */
    List<java.util.Map<String, Object>> countStudentByClazz();
}
