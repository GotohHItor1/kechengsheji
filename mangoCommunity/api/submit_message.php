<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';


if (!isset($_SESSION['is_login']) || $_SESSION['is_login'] !== true) {
    echo json_encode(['code' => 0, 'msg' => '请先登录']);
    exit;
}

$user_id = $_SESSION['user_id'];
$content = $_POST['content'] ?? '';

if (empty($content)) {
    echo json_encode(['code' => 0, 'msg' => '留言内容不能为空']);
    exit;
}


$insert_sql = "INSERT INTO message_board (user_id, content) VALUES ($user_id, '$content')";
if (mysqli_query($conn, $insert_sql)) {
    echo json_encode(['code' => 1, 'msg' => '留言成功']);
} else {
    echo json_encode(['code' => 0, 'msg' => '留言失败：' . mysqli_error($conn)]);
}

mysqli_close($conn);
?>