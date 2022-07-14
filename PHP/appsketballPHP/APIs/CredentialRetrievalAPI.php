<?php
	$data = array();
	$username = $_GET["username"];


	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="test";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "SELECT * FROM registry WHERE username = '$username'";
	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$data['id'] = $row['id'];
		$data['username'] = $row['username'];
		$data['email'] = $row['email'];
		$data['password'] = $row['password'];
		$data['isAdmin'] = $row['isAdmin'];
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>