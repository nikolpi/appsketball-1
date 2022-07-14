<!DOCTYPE HTML>
<html>
<head>
</head>
<body>	

<?php
// define variables and set to empty values
$name = $town = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (empty($_POST["name"])) {
   } else {
	   $name = test_input($_POST["name"]);
   }
   if (empty($_POST["town"])) {
   } else {
	   $town = test_input($_POST["town"]);
   }
   if (empty($_POST["badge"])) {
   } else {
	   $badge = test_input($_POST["badge"]);
   }
}
function test_input($data) {
 $data = trim($data);
 $data = stripslashes($data);
 $data = htmlspecialchars($data);
 return $data;
}
?>
<h2>Δημιουργία Ομάδας</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
 Όνομα: <input type="text" name="name">
 <br><br>
 Πόλη: <input type="text" name="town">
 <br><br>
 Λογότυπο/Σήμα: <input type="text" name="badge">
 <br><br>
 
 <input type="submit" name="submit" value="Submit">
 <br><br>
 <input type="submit" name="create" value="Create DB">
</form>
<br>

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
	$sql = "CREATE TABLE MyTeams (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	town VARCHAR(30) NOT NULL,
	badge VARCHAR(500) NOT NULL
	)";
	if ($conn->query($sql) === TRUE) {
	 echo "Table MyTeams created successfully";
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
	$sql = "INSERT INTO MyTeams (name, town, badge)
	VALUES ('$name', '$town', '$badge')";
	if ($conn->query($sql) === TRUE) {
	 echo "New record created successfully";
	} else {
	 echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
}
?> 