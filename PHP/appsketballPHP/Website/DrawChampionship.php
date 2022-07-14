<?php

//Connect to our MySQL database using the PDO extension.
$pdo = new PDO('mysql:host=localhost;dbname=test', 'root', '');

//Our select statement. This will retrieve the data that we want.
$sql = "SELECT chpname , team FROM MyChampionship";

//Prepare the select statement.
$stmt = $pdo->prepare($sql);

//Execute the statement.
$stmt->execute();

//Retrieve the rows using fetchAll.
$users = $stmt->fetchAll();

?>

<?php 
 $stack = array();
 $temp = 0;

 foreach($users as $user):
	if($user['chpname'] != $temp ){
		array_push($stack, $user['chpname']);
	}
	$temp = $user['chpname'];
 endforeach;
?>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<?php
// define variables and set to empty values
$choose = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (empty($_POST["choose"])) {
   } else {
	   $choose = test_input($_POST["choose"]);
   }
}
function test_input($data) {
 $data = trim($data);
 $data = stripslashes($data);
 $data = htmlspecialchars($data);
 return $data;
}
?>
<h2>Κλήρωση Πρωταθλήματος</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
 Διαλέξτε Πρωτάθλημα:
  <select name = 'choose'>
	<?php foreach($stack as $opt): ?>
		<option value="<?= $opt; ?>">
			<?= $opt; ?> 
		</option>
	<?php endforeach; ?>
 </select>
 <br><br>
 
 <input type="submit" name="submit" value="Κλήρωση">
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
	$sql = "CREATE TABLE DrawChampionship (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	chpname VARCHAR(30) NOT NULL,
	pres VARCHAR(30) NOT NULL,
	team1 VARCHAR(30) NOT NULL,
	team2 VARCHAR(30) NOT NULL
	)";
	if ($conn->query($sql) === TRUE) {
	 echo "Table DrawChampionship created successfully";
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
	
	$teams = array();
	
	 foreach($users as $user):
		if($user['chpname'] == $choose ){
			array_push($teams, $user['team']);
		}
	 endforeach;
	
	//shuffle($teams);
	
	$matches = [[]];
	$count = 0;
	
	for($i = 0; $i < count($teams); $i++){
		for($j = $i + 1; $j < count($teams); $j++){
			$matches[$count][0] = $teams[$i];
			$matches[$count][1] = $teams[$j];
			$count++;
		}
	}
	 
	 $count = 0;
	 for($i=0;$i<(count($teams)-1);$i++){
		 for($j=0;$j<(count($teams)/2);$j++){
			$value1 = $matches[$count][0];
			$value2 = $matches[$count][1];
			$sql = "INSERT INTO DrawChampionship (chpname, pres, team1, team2)
			VALUES ('$choose', '$i', '$value1' , '$value2')";
			$count++;
			echo "<br>";
			if ($conn->query($sql) === TRUE) {
			 echo "New record created successfully";
			} else {
			 echo "Error: " . $sql . "<br>" . $conn->error;
			}
		 }
	 }
	$conn->close();
	
}
?>