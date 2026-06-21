package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Emp;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 */
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private EmpService empService;

    /**
     * 用户登录
     * POST /login
     * Body: { "username": "admin", "password": "123456" }
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录请求: username={}", emp.getUsername());

        Emp loginEmp = empService.login(emp.getUsername(), emp.getPassword());
        if (loginEmp == null) {
            return Result.error("用户名或密码错误");
        }

        // 生成 JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("empId", loginEmp.getId());
        claims.put("username", loginEmp.getUsername());
        String token = JwtUtils.generateJwt(claims);

        // 返回令牌
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("empId", loginEmp.getId());
        data.put("name", loginEmp.getName());
        return Result.success(data);
    }
}
