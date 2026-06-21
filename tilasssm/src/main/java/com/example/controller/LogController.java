package com.example.controller;

import com.example.dto.PageBean;
import com.example.dto.Result;
import com.example.mapper.LogMapper;
import com.example.pojo.OperateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操作日志控制器
 */
@RestController
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogMapper logMapper;

    /**
     * GET /log/page - 操作日志分页查询
     * @param page     页码 (默认1)
     * @param pageSize 每页条数 (默认10)
     */
    @GetMapping("/log/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询操作日志: page={}, pageSize={}", page, pageSize);

        List<OperateLog> allRows = logMapper.page();
        long total = allRows.size();

        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= total) {
            return Result.success(new PageBean(total, java.util.Collections.emptyList()));
        }
        int toIndex = Math.min(fromIndex + pageSize, (int) total);
        List<OperateLog> pageRows = allRows.subList(fromIndex, toIndex);

        return Result.success(new PageBean(total, pageRows));
    }
}
