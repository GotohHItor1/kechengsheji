package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理控制器
 */
@RestController
@RequestMapping("/depts")
public class DeptController {

    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /** GET /depts - 查询所有部门 */
    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        return Result.success(deptService.list());
    }

    /** POST /depts - 新增部门 */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept.getName());
        deptService.add(dept);
        return Result.success();
    }

    /** DELETE /depts?id={id} - 删除部门 */
    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        log.info("删除部门: id={}", id);
        deptService.delete(id);
        return Result.success();
    }

    /** GET /depts/{id} - 根据ID查询部门 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询部门: id={}", id);
        return Result.success(deptService.getById(id));
    }

    /** PUT /depts - 修改部门 */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门: id={}, name={}", dept.getId(), dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}
