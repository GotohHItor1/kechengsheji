package com.example.service.impl;

import com.example.dto.PageBean;
import com.example.mapper.ClazzMapper;
import com.example.pojo.Clazz;
import com.example.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 班级业务实现
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    private static final Logger log = LoggerFactory.getLogger(ClazzServiceImpl.class);

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageBean page(String name, String begin, String end, Short status, Integer page, Integer pageSize) {
        List<Clazz> allRows = clazzMapper.list(name, begin, end, status);
        long total = allRows.size();

        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= total) {
            return new PageBean(total, java.util.Collections.emptyList());
        }
        int toIndex = Math.min(fromIndex + pageSize, (int) total);
        List<Clazz> pageRows = allRows.subList(fromIndex, toIndex);

        return new PageBean(total, pageRows);
    }

    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }

    @Override
    @Transactional
    public void add(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    @Transactional
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
}
