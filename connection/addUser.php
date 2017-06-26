<?php
require_once('dbConnect.php');

if ($_SERVER['REQUEST_METHOD']=='POST') {
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql  = "INSERT INTO tbl_users (username, password) VALUES ('$username', '$password')";

	if(mysqli_query($conn, $sql)) {
		echo "Added Successfully";
	}
	else {
		echo "Could not Add";
	}
	mysql_close($conn);
}
?>
