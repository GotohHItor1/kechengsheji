package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 配置类
 * 注意: 本项目主要在 spring-mybatis.xml 中配置 MyBatis
 * 此配置类仅用于 MapperScan 注解扫描
 */
@Configuration
@MapperScan("com.example.mapper")
public class MyBatisConfig {
}
