<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';

if (!isset($_SESSION['is_login']) || $_SESSION['is_login'] !== true) {
    echo json_encode(['code' => 0, 'msg' => '请先登录']);
    exit;
}

$user_id = $_SESSION['user_id'];
$activity_id = $_POST['activity_id'] ?? '';

if (empty($activity_id)) {
    echo json_encode(['code' => 0, 'msg' => '活动ID不能为空']);
    exit;
}

$check_sql = "SELECT id FROM volunteer_apply WHERE user_id = $user_id AND activity_id = $activity_id";
$check_result = mysqli_query($conn, $check_sql);
if (mysqli_num_rows($check_result) > 0) {
    echo json_encode(['code' => 0, 'msg' => '您已报名该活动，无需重复报名']);
    exit;
}

$insert_sql = "INSERT INTO volunteer_apply (user_id, activity_id) VALUES ($user_id, $activity_id)";
if (mysqli_query($conn, $insert_sql)) {
    echo json_encode(['code' => 1, 'msg' => '报名成功']);
} else {
    echo json_encode(['code' => 0, 'msg' => '报名失败：' . mysqli_error($conn)]);
}

mysqli_close($conn);
?>