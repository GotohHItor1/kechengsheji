package com.example.mapper;

import com.example.pojo.Expr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工工作经历 Mapper 接口
 */
public interface ExprMapper {

    /** 根据员工ID查询工作经历 */
    List<Expr> getByEmpId(Integer empId);

    /** 批量新增工作经历 */
    void insertBatch(@Param("list") List<Expr> exprList);

    /** 根据员工ID删除工作经历 */
    void deleteByEmpId(Integer empId);
}
