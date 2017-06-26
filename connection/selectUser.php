<?php
require_once('dbConnect.php');

if ($_SERVER['REQUEST_METHOD']=='POST') {
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql  	= "SELECT * FROM tbl_user WHERE username = '$username' AND password = '$password'";
	$result = mysqli_query($conn, $sql);

	 while($row = mysqli_fetch_array($result)){
	 	if ($username == $row['username'] && $password == $row['password']) {
	 		echo "Login Successfully";
	 	}
	 	else {
	 		echo "Failed";
	 	}
 	 }
 	 mysqli_close($conn);
  }
?>
