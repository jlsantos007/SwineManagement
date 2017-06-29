<?php
require_once('dbConnect.php');

if ($_SERVER['REQUEST_METHOD']=='POST') {
	$username = $_POST['username'];
	$password = $_POST['password'];

	$check  = "SELECT * FROM tbl_users WHERE username = '$username'";
	$result = mysqli_query($conn, $check);
	$row 	= mysqli_num_rows($result);

	if ($row == 0) {
		$sql  = "INSERT INTO tbl_users (username, password) VALUES ('$username', '$password')";

		if(mysqli_query($conn, $sql)) {
			echo "Added Successfully";
		}
		else {
			echo "Please fill the empty fields";
		}
	}
	else {
		echo "Username Already Exists";
	}

	mysqli_close($conn);
}
?>
