<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<?php
// define variables and set to empty values
$name = $pos = $team = $pic = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (empty($_POST["name"])) {
   } else {
	   $name = test_input($_POST["name"]);
   }
   if (empty($_POST["pos"])) {
   } else {
	   $pos = test_input($_POST["pos"]);
   }
   if (empty($_POST["team"])) {
   } else {
	   $team = test_input($_POST["team"]);
   }
   if (empty($_POST["pic"])) {
   } else {
	   $pic = test_input($_POST["pic"]);
   }
}
function test_input($data) {
 $data = trim($data);
 $data = stripslashes($data);
 $data = htmlspecialchars($data);
 return $data;
}
?>
<h2>Δημιουργία Παίκτη</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
 Ονοματεπώνυμο: <input type="text" name="name">
 <br><br>
 Θέση: <input type="text" name="pos">
 <br><br>
 Τρέχουσα Ομάδα: <input type="text" name="team">
 <br><br>
 Φωτογραφία: <input type="text" name="pic">
 <br><br>
 
 <input type="submit" name="submit" value="Submit">
 <br><br>
 <input type="submit" name="create" value="Create DB">
 <br><br>
</form>

<form method="post" action="Main.php">
 <input type="submit" name="view" value="Main">
 <br><br>
</form>

</body>
</html>

<?php

if (isset($_POST['create'])) {
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "test";
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
	 die("Connection failed: " . $conn->connect_error);
	}
	// sql to create table
	$sql = "CREATE TABLE MyPlayers (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	pos VARCHAR(30) NOT NULL,
	team VARCHAR(30) NOT NULL,
	pic VARCHAR(500) NOT NULL
	)";
	if ($conn->query($sql) === TRUE) {
	 echo "Table MyPlayers created successfully";
	} else {
	 echo "Error creating table: " . $conn->error;
	}
	$conn->close(); 
}
?> 

<?php

if (isset($_POST['submit'])) {

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "test";
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
	 die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO MyPlayers (name, pos, team, pic)
	VALUES ('$name', '$pos', '$team', '$pic')";
	if ($conn->query($sql) === TRUE) {
	 echo "New record created successfully";
	} else {
	 echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
}
?> 