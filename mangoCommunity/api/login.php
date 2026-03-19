<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';

$email = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';
$remember_me = $_POST['rememberMe'] ?? 0;

if (empty($email) || empty($password)) {
    echo json_encode(['code' => 0, 'msg' => '请输入邮箱和密码']);
    exit;
}

$password_md5 = md5($password);
$login_sql = "SELECT id, full_name, nickname FROM user WHERE email = '$email' AND password = '$password_md5'";
$result = mysqli_query($conn, $login_sql);
$user = mysqli_fetch_assoc($result);

if ($user) {
    $_SESSION['is_login'] = true;
    $_SESSION['user_id'] = $user['id'];
    $_SESSION['user_name'] = $user['full_name'];
    $_SESSION['user_nickname'] = $user['nickname'] ?: $user['full_name'];
    $_SESSION['user_email'] = $email;
    
    if ($remember_me == 1) {
        setcookie(session_name(), session_id(), time() + 7*24*3600, '/');
    }
    
    echo json_encode(['code' => 1, 'msg' => '登录成功', 'data' => $user]);
} else {
    echo json_encode(['code' => 0, 'msg' => '邮箱或密码错误']);
}

mysqli_close($conn);
?>