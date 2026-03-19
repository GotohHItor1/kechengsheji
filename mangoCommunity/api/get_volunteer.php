<?php
header("Content-Type: application/json; charset=utf-8");
include '../config/db.php';

$sql = "SELECT id, title, description, service_time, requirements FROM volunteer_activity ORDER BY id ASC";
$result = mysqli_query($conn, $sql);
$activities = [];
if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $activities[] = $row;
    }
}

echo json_encode(['code' => 1, 'data' => $activities]);
mysqli_close($conn);
?>