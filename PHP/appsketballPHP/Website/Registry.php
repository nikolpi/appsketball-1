<!DOCTYPE HTML>
<html>
<head>
</head>
<body>

<h2>Registry</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
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
	$sql = "CREATE TABLE Registry (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	password VARCHAR(30) NOT NULL,
	isAdmin BOOLEAN NOT NULL
	)";
	if ($conn->query($sql) === TRUE) {
	 echo "Table Registry created successfully";
	} else {
	 echo "Error creating table: " . $conn->error;
	}
	$conn->close(); 
}
?>

 <?php

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
$sql = "SELECT id, username, email , password, isAdmin FROM Registry";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	// output data of each row
	while($row = $result->fetch_assoc()) {
		echo "id: " . $row["id"]. " - Name: " . $row["username"]. " - Email: " . $row["email"]. " - Password: " . $row["password"]. " - isAdmin: " . $row["isAdmin"]. "<br>";
	}
} else {
	echo "0 results";
}
$conn->close();
	

 ?>