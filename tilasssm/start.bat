@echo off
chcp 65001 >nul
title Tlias 智能学习辅助系统

echo ============================================
echo    Tlias 智能学习辅助系统 - 启动中...
echo ============================================
echo.

:: 检查 Maven 是否可用
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo [错误] 未找到 Maven，请先安装 Maven 并配置环境变量
    pause
    exit /b 1
)

echo [1/2] 清理并构建项目...
call mvn clean compile -DskipTests -q
if %errorlevel% neq 0 (
    echo [错误] 编译失败，请检查代码
    pause
    exit /b 1
)

echo [2/2] 启动嵌入式 Tomcat...
echo.
echo   服务地址: http://localhost:8080/tilasssm
echo   登录接口: POST http://localhost:8080/tilasssm/login
echo   默认账号: admin / 123456
echo.
echo   按 Ctrl+C 停止服务
echo ============================================
echo.

call mvn tomcat7:run
pause
