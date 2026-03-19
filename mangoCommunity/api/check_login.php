<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';

if (isset($_SESSION['is_login']) && $_SESSION['is_login'] === true) {
    echo json_encode([
        'code' => 1,
        'msg' => '已登录',
        'data' => [
            'user_id' => $_SESSION['user_id'],
            'user_name' => $_SESSION['user_name']
        ]
    ]);
} else {
    echo json_encode(['code' => 0, 'msg' => '未登录']);
}

mysqli_close($conn);
?>