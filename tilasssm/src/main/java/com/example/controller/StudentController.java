package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学员管理控制器
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    /**
     * GET /students - 条件分页查询学员
     * @param name    姓名 (模糊)
     * @param degree  学历
     * @param clazzId 班级ID
     * @param page     页码 (默认1)
     * @param pageSize 每页条数 (默认10)
     */
    @GetMapping
    public Result list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Short degree,
                       @RequestParam(required = false) Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("条件分页查询学员: name={}, degree={}, clazzId={}, page={}, pageSize={}",
                name, degree, clazzId, page, pageSize);
        return Result.success(studentService.page(name, degree, clazzId, page, pageSize));
    }

    /** POST /students - 新增学员 */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("新增学员: {}", student.getName());
        studentService.add(student);
        return Result.success();
    }

    /** DELETE /students/{ids} - 批量删除学员 (ids 用逗号分隔，如: /students/1,2,3) */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学员: ids={}", ids);
        // 将逗号分隔的 ID 字符串转为 List<Integer>
        String[] idArray = ids.split(",");
        java.util.List<Integer> idList = new java.util.ArrayList<>();
        for (String idStr : idArray) {
            idList.add(Integer.parseInt(idStr.trim()));
        }
        studentService.delete(idList);
        return Result.success();
    }

    /** GET /students/{id} - 根据ID查询学员 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询学员: id={}", id);
        return Result.success(studentService.getById(id));
    }

    /** PUT /students - 修改学员 */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员: id={}", student.getId());
        studentService.update(student);
        return Result.success();
    }

    /** PUT /students/violation/{id}/{score} - 学员违纪扣分 */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪扣分: id={}, score={}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
