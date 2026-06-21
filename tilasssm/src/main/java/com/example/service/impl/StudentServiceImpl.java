package com.example.service.impl;

import com.example.dto.PageBean;
import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学员业务实现
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean page(String name, Short degree, Integer clazzId, Integer page, Integer pageSize) {
        List<Student> allRows = studentMapper.list(name, degree, clazzId);
        long total = allRows.size();

        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= total) {
            return new PageBean(total, java.util.Collections.emptyList());
        }
        int toIndex = Math.min(fromIndex + pageSize, (int) total);
        List<Student> pageRows = allRows.subList(fromIndex, toIndex);

        return new PageBean(total, pageRows);
    }

    @Override
    @Transactional
    public void add(Student student) {
        studentMapper.insert(student);
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    @Transactional
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    @Transactional
    public void violation(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }
}
