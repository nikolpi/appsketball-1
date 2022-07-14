<?php
	$data = array();
	$id = $_GET["id"];
	$username = $_GET["username"];
	$email = $_GET["email"];
	$password = $_GET["password"];
	$isAdmin = $_GET["isAdmin"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="test";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "INSERT into registry values('" . $id . "', '" . $username . "','" . $email . "','" . $password . "', '" . $isAdmin ."')";
	echo $sql;
	mysqli_query($dbh, $sql);
	mysqli_close($dbh);


?>