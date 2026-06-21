package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Emp;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制器
 */
@RestController
@RequestMapping("/emps")
public class EmpController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    /**
     * GET /emps - 条件分页查询员工
     * @param name     姓名 (模糊)
     * @param gender   性别
     * @param begin    入职日期起始
     * @param end      入职日期截止
     * @param page     页码 (默认1)
     * @param pageSize 每页条数 (默认10)
     */
    @GetMapping
    public Result list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Short gender,
                       @RequestParam(required = false) String begin,
                       @RequestParam(required = false) String end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("条件分页查询员工: name={}, gender={}, begin={}, end={}, page={}, pageSize={}",
                name, gender, begin, end, page, pageSize);
        return Result.success(empService.page(name, gender, begin, end, page, pageSize));
    }

    /** GET /emps/list - 查询全部员工 */
    @GetMapping("/list")
    public Result listAll() {
        log.info("查询全部员工");
        return Result.success(empService.listAll());
    }

    /** POST /emps - 新增员工 */
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp.getName());
        empService.add(emp);
        return Result.success();
    }

    /** DELETE /emps?ids={id1},{id2},... - 批量删除员工 */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除员工: ids={}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /** GET /emps/{id} - 根据ID查询员工 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工: id={}", id);
        return Result.success(empService.getById(id));
    }

    /** PUT /emps - 修改员工 */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: id={}", emp.getId());
        empService.update(emp);
        return Result.success();
    }
}
