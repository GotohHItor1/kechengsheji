-- Tlias 智能学习辅助系统 数据库初始化脚本
-- MySQL 8.0+

CREATE DATABASE IF NOT EXISTS tlias DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE tlias;

-- ============================================================
-- 部门表
-- ============================================================
DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name        VARCHAR(50)  NOT NULL COMMENT '部门名称',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ============================================================
-- 员工表
-- ============================================================
DROP TABLE IF EXISTS emp;
CREATE TABLE emp
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(128) NOT NULL COMMENT '密码',
    name        VARCHAR(50)  NOT NULL COMMENT '员工姓名',
    gender      TINYINT      NOT NULL DEFAULT 1 COMMENT '性别: 1-男, 2-女',
    phone       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    job         TINYINT      DEFAULT NULL COMMENT '职位: 1-班主任, 2-讲师, 3-学工主管, 4-教研主管',
    salary      INT          DEFAULT NULL COMMENT '薪资',
    image       VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    entry_date  DATE         DEFAULT NULL COMMENT '入职日期',
    dept_id     INT UNSIGNED DEFAULT NULL COMMENT '所属部门ID',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- ============================================================
-- 员工工作经历表
-- ============================================================
DROP TABLE IF EXISTS emp_expr;
CREATE TABLE emp_expr
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    emp_id      INT UNSIGNED NOT NULL COMMENT '员工ID',
    begin       DATE         NOT NULL COMMENT '开始时间',
    end         DATE         NOT NULL COMMENT '结束时间',
    company     VARCHAR(100) NOT NULL COMMENT '公司名称',
    job         VARCHAR(50)  NOT NULL COMMENT '职位',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工工作经历表';

-- ============================================================
-- 班级表
-- ============================================================
DROP TABLE IF EXISTS clazz;
CREATE TABLE clazz
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name        VARCHAR(100) NOT NULL COMMENT '班级名称',
    room        VARCHAR(50)  DEFAULT NULL COMMENT '教室',
    begin_date  DATE         DEFAULT NULL COMMENT '开课时间',
    end_date    DATE         DEFAULT NULL COMMENT '结课时间',
    student_num INT          NOT NULL DEFAULT 0 COMMENT '学员人数',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态: 1-在读, 2-已结课',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- ============================================================
-- 学员表
-- ============================================================
DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name        VARCHAR(50)  NOT NULL COMMENT '学员姓名',
    gender      TINYINT      NOT NULL DEFAULT 1 COMMENT '性别: 1-男, 2-女',
    phone       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    degree      TINYINT      DEFAULT NULL COMMENT '学历: 1-初中, 2-高中, 3-大专, 4-本科, 5-硕士, 6-博士',
    violation   INT          NOT NULL DEFAULT 0 COMMENT '违纪次数',
    score       INT          NOT NULL DEFAULT 0 COMMENT '违纪扣分',
    clazz_id    INT UNSIGNED DEFAULT NULL COMMENT '所属班级ID',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学员表';

-- ============================================================
-- 操作日志表
-- ============================================================
DROP TABLE IF EXISTS operate_log;
CREATE TABLE operate_log
(
    id            INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    operate_emp   INT UNSIGNED DEFAULT NULL COMMENT '操作人ID',
    operate_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    class_name    VARCHAR(255) DEFAULT NULL COMMENT '操作类名',
    method_name   VARCHAR(100) DEFAULT NULL COMMENT '操作方法名',
    method_params VARCHAR(2000) DEFAULT NULL COMMENT '方法参数',
    return_value  VARCHAR(2000) DEFAULT NULL COMMENT '返回值',
    cost_time     BIGINT       DEFAULT NULL COMMENT '方法耗时(ms)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ============================================================
-- 初始数据
-- ============================================================

-- 部门数据
INSERT INTO dept (name) VALUES
('学工部'),
('教研部'),
('咨询部'),
('就业部'),
('人事部');

-- 员工数据 (密码为 123456 的简单编码)
INSERT INTO emp (username, password, name, gender, phone, job, salary, dept_id, entry_date) VALUES
('admin',    '123456', '管理员',   1, '13800000000', 3,  8000, 1, '2020-01-01'),
('zhangsan', '123456', '张三',     1, '13800000001', 1,  7000, 1, '2021-03-15'),
('lisi',     '123456', '李四',     2, '13800000002', 2,  9000, 2, '2021-06-01'),
('wangwu',   '123456', '王五',     1, '13800000003', 2,  8500, 2, '2022-01-10'),
('zhaoliu',  '123456', '赵六',     2, '13800000004', 1,  6500, 1, '2022-05-20'),
('sunqi',    '123456', '孙七',     1, '13800000005', 4,  9500, 2, '2020-09-01');

-- 班级数据
INSERT INTO clazz (name, room, begin_date, end_date, student_num, status) VALUES
('Java 就业班 2023-01 期',  '301',   '2023-01-10', '2023-07-10', 35, 2),
('Java 就业班 2023-06 期',  '302',   '2023-06-01', '2023-12-01', 40, 2),
('Java 就业班 2024-01 期',  '303',   '2024-01-15', '2024-07-15', 38, 1),
('前端就业班 2024-03 期',   '401',   '2024-03-01', '2024-09-01', 30, 1),
('Python 就业班 2024-02 期','402',   '2024-02-20', '2024-08-20', 28, 1);

-- 学员数据
INSERT INTO student (name, gender, phone, degree, violation, score, clazz_id) VALUES
('小明', 1, '13900000001', 3, 2, 10, 1),
('小红', 2, '13900000002', 4, 0, 0,  1),
('小刚', 1, '13900000003', 2, 1, 5,  1),
('小丽', 2, '13900000004', 4, 0, 0,  2),
('小强', 1, '13900000005', 3, 3, 15, 2),
('小美', 2, '13900000006', 5, 0, 0,  2),
('小虎', 1, '13900000007', 2, 1, 5,  3),
('小芳', 2, '13900000008', 4, 0, 0,  3),
('小龙', 1, '13900000009', 3, 2, 10, 3),
('小燕', 2, '13900000010', 4, 0, 0,  4),
('小飞', 1, '13900000011', 1, 4, 20, 4),
('小兰', 2, '13900000012', 3, 0, 0,  5),
('小杰', 1, '13900000013', 2, 1, 5,  5);
