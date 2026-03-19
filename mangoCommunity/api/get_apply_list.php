<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';


if (!isset($_SESSION['is_login']) || $_SESSION['is_login'] !== true) {
    echo json_encode(['code' => 0, 'msg' => '请先登录']);
    exit;
}


$user_id = $_SESSION['user_id'];
$sql = "SELECT va.id, va.title, va.service_time, v.apply_time 
        FROM volunteer_apply v 
        LEFT JOIN volunteer_activity va ON v.activity_id = va.id 
        WHERE v.user_id = $user_id 
        ORDER BY v.apply_time DESC";

$result = mysqli_query($conn, $sql);
$list = [];
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $list[] = $row;
    }
}

echo json_encode(['code' => 1, 'data' => $list]);
mysqli_close($conn);
?>