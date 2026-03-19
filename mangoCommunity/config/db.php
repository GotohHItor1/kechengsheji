<?php
$host = 'localhost';
$user = 'root';
$pwd = '';
$dbname = 'mango_community';

$conn = mysqli_connect($host, $user, $pwd, $dbname);

if (!$conn) {
    die("数据库连接失败: " . mysqli_connect_error());
}

mysqli_set_charset($conn, 'utf8mb4');
?>