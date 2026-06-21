package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring 核心配置类
 * 注意: 本项目使用 XML 配置方式 (spring-mybatis.xml, spring-mvc.xml)
 * 此配置类作为补充，扫描 Service 层组件
 */
@Configuration
@ComponentScan("com.example.service")
@EnableTransactionManagement
@Import({MyBatisConfig.class})
public class SpringConfig {
}
