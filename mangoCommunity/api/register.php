<?php
header("Content-Type: application/json; charset=utf-8");
include '../config/db.php';

$full_name = $_POST['fullName'] ?? '';
$email = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';
$phone = $_POST['phone'] ?? '';

if (empty($full_name) || empty($email) || empty($password)) {
    echo json_encode(['code' => 0, 'msg' => '姓名、邮箱、密码为必填项']);
    exit;
}

$password_md5 = md5($password);

$check_sql = "SELECT id FROM user WHERE email = '$email'";
$check_result = mysqli_query($conn, $check_sql);
if (mysqli_num_rows($check_result) > 0) {
    echo json_encode(['code' => 0, 'msg' => '该邮箱已注册，请更换邮箱']);
    exit;
}

$insert_sql = "INSERT INTO user (full_name, email, password, phone) VALUES ('$full_name', '$email', '$password_md5', '$phone')";
if (mysqli_query($conn, $insert_sql)) {
    echo json_encode(['code' => 1, 'msg' => '注册成功，请登录']);
} else {
    echo json_encode(['code' => 0, 'msg' => '注册失败：' . mysqli_error($conn)]);
}

mysqli_close($conn);
?>