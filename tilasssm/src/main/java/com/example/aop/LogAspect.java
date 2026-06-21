package com.example.aop;

import com.example.mapper.LogMapper;
import com.example.pojo.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志 AOP 切面
 * 自动记录 Controller 层方法的调用日志 (操作人、耗时、参数、返回值等)
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogMapper logMapper;

    /**
     * 环绕通知：拦截 com.example.controller 包下所有 public 方法
     */
    @Around("execution(* com.example.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        // 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } finally {
            long costTime = System.currentTimeMillis() - begin;

            // 构建日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(joinPoint.getTarget().getClass().getName());
            operateLog.setMethodName(joinPoint.getSignature().getName());
            // 参数截断，避免过长
            String params = Arrays.toString(joinPoint.getArgs());
            if (params.length() > 2000) {
                params = params.substring(0, 2000);
            }
            operateLog.setMethodParams(params);
            // 返回值截断
            String returnStr = result != null ? result.toString() : "null";
            if (returnStr.length() > 2000) {
                returnStr = returnStr.substring(0, 2000);
            }
            operateLog.setReturnValue(returnStr);
            operateLog.setCostTime(costTime);

            try {
                logMapper.insert(operateLog);
            } catch (Exception e) {
                log.error("记录操作日志失败", e);
            }

            log.info("{} {} 耗时: {}ms", operateLog.getClassName(), operateLog.getMethodName(), costTime);
        }
    }
}
