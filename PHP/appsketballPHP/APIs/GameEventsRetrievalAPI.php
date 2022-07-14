<?php
	$data = array();
	$game = $_GET["game"];
	$id = $_GET["id"]; // We need the id to determine if we have a new event while the game is still live


	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="test";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "SELECT gameevents.id, gameevents.date, gameevents.game,
					gameevents.playerName, gameevents.event, gameevents.quarter,
					 myplayers.team
			FROM gameevents
			LEFT JOIN myplayers
			ON gameevents.playerName = myplayers.name
			WHERE gameevents.game = '$game' AND gameevents.id > '$id' ";
	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
        $data[] = array(
            "id" => $row['id'],
            "date" => $row['date'], 
            "game" => $row['game'],
            "playerName" => $row['playerName'],
            "event" => $row['event'],
			"quarter" => $row['quarter'],
			"team" => $row['team']
        );
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>