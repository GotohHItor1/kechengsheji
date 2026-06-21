package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Clazz;
import com.example.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 班级管理控制器
 */
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    private static final Logger log = LoggerFactory.getLogger(ClazzController.class);

    @Autowired
    private ClazzService clazzService;

    /**
     * GET /clazzs - 条件分页查询班级
     * @param name     班级名称 (模糊)
     * @param begin    开课时间起始
     * @param end      开课时间截止
     * @param status   状态
     * @param page     页码 (默认1)
     * @param pageSize 每页条数 (默认10)
     */
    @GetMapping
    public Result list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String begin,
                       @RequestParam(required = false) String end,
                       @RequestParam(required = false) Short status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("条件分页查询班级: name={}, begin={}, end={}, status={}, page={}, pageSize={}",
                name, begin, end, status, page, pageSize);
        return Result.success(clazzService.page(name, begin, end, status, page, pageSize));
    }

    /** GET /clazzs/list - 查询全部班级 */
    @GetMapping("/list")
    public Result listAll() {
        log.info("查询全部班级");
        return Result.success(clazzService.listAll());
    }

    /** POST /clazzs - 新增班级 */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz.getName());
        clazzService.add(clazz);
        return Result.success();
    }

    /** DELETE /clazzs/{id} - 删除班级 */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级: id={}", id);
        clazzService.delete(id);
        return Result.success();
    }

    /** GET /clazzs/{id} - 根据ID查询班级 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询班级: id={}", id);
        return Result.success(clazzService.getById(id));
    }

    /** PUT /clazzs - 修改班级 */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级: id={}", clazz.getId());
        clazzService.update(clazz);
        return Result.success();
    }
}
