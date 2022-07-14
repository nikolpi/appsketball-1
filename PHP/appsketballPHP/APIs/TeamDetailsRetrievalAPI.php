<?php
	$data = array();
	$name = $_GET["name"];


	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="test";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "SELECT * FROM myteams WHERE name = '$name'";
	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$data['id'] = $row['id'];
		$data['name'] = $row['name'];
		$data['badge'] = $row['badge'];
		$data['town'] = $row['town'];
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>