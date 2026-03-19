<?php
header("Content-Type: application/json; charset=utf-8");
session_start();
include '../config/db.php';


$sql = "SELECT m.id, m.content, m.create_time, u.full_name, u.nickname 
        FROM message_board m 
        LEFT JOIN user u ON m.user_id = u.id 
        ORDER BY m.create_time DESC";

$result = mysqli_query($conn, $sql);
$messages = [];
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $messages[] = [
            'id' => $row['id'],
            'content' => $row['content'],
            'create_time' => $row['create_time'],
            'username' => $row['nickname'] ?: $row['full_name']
        ];
    }
}

echo json_encode(['code' => 1, 'data' => $messages]);
mysqli_close($conn);
?>