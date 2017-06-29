<?php
require_once('dbConnect.php');

if ($_SERVER['REQUEST_METHOD']=='POST') {
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql  	= "SELECT * FROM tbl_users WHERE username = '$username' AND password = '$password'";
	$result = mysqli_query($conn, $sql);
	$row 	= mysqli_num_rows($result);

	if($row == 0) { 
        echo "No Such User Found"; 
    }
    else  {
        echo "Login Successfully"; 
    }
 	 mysqli_close($conn);
  }
?>