<?php
define('hostname', 'localhost');
define('username', 'root');
define('password', '');
define('db_name', 'db_credentials');

$conn = mysqli_connect(hostname, username, password, db_name) or die ('Unable to Connect');
?>
