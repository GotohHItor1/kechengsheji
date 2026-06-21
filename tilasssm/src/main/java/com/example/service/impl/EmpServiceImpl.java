package com.example.service.impl;

import com.example.dto.PageBean;
import com.example.mapper.EmpMapper;
import com.example.mapper.ExprMapper;
import com.example.pojo.Emp;
import com.example.pojo.Expr;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 员工业务实现
 */
@Service
public class EmpServiceImpl implements EmpService {

    private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ExprMapper exprMapper;

    @Override
    public PageBean page(String name, Short gender, String begin, String end, Integer page, Integer pageSize) {
        List<Emp> allRows = empMapper.list(name, gender, begin, end);
        long total = allRows.size();

        // 手动分页
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= total) {
            return new PageBean(total, java.util.Collections.emptyList());
        }
        int toIndex = Math.min(fromIndex + pageSize, (int) total);
        List<Emp> pageRows = allRows.subList(fromIndex, toIndex);

        return new PageBean(total, pageRows);
    }

    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    @Override
    @Transactional
    public void add(Emp emp) {
        // 数据库存明文密码，直接插入
        empMapper.insert(emp);

        // 插入工作经历
        List<Expr> exprList = emp.getExprList();
        if (exprList != null && !exprList.isEmpty()) {
            for (Expr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            exprMapper.insertBatch(exprList);
        }
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        // 同时删除关联的工作经历
        for (Integer empId : ids) {
            exprMapper.deleteByEmpId(empId);
        }
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    @Transactional
    public void update(Emp emp) {
        empMapper.update(emp);

        // 更新工作经历: 先删后增
        List<Expr> exprList = emp.getExprList();
        if (exprList != null && !exprList.isEmpty()) {
            exprMapper.deleteByEmpId(emp.getId());
            for (Expr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            exprMapper.insertBatch(exprList);
        }
    }

    @Override
    public Emp login(String username, String password) {
        Emp emp = empMapper.getByUsername(username);
        if (emp == null) {
            return null;
        }
        // 明文密码比对
        if (!password.equals(emp.getPassword())) {
            return null;
        }
        return emp;
    }
}
