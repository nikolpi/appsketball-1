<?php

//Connect to our MySQL database using the PDO extension.
$pdo = new PDO('mysql:host=localhost;dbname=test', 'root', '');

//Our select statement. This will retrieve the data that we want.
$sql = "SELECT id, name FROM MyTeams";

//Prepare the select statement.
$stmt = $pdo->prepare($sql);

//Execute the statement.
$stmt->execute();

//Retrieve the rows using fetchAll.
$users = $stmt->fetchAll();

?>

<!DOCTYPE HTML>
<html>
<head>
</head>
<style>
      select>option{ 
                   height:30px;
				   width:200px;
                 }
</style>
<body>
<?php
// define variables and set to empty values
$chpname = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (empty($_POST["chpname"])) {
   } else {
	   $chpname = test_input($_POST["chpname"]);
   }
}
function test_input($data) {
 $data = trim($data);
 $data = stripslashes($data);
 $data = htmlspecialchars($data);
 return $data;
}
?>
<h2>Δημιουργία Πρωταθλήματος</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
 Όνομα Πρωταθλήματος: <input type="text" name="chpname">
 <br><br>
 
 Διαλέξτε ομάδες:
 <br>
 <select name = 'subject[]' multiple>
	<?php foreach($users as $user): ?>
		<option value="<?= $user['name']; ?>"><?= $user['name']; ?></option>
	<?php endforeach; ?>
 </select>
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
	$sql = "CREATE TABLE MyChampionship (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	chpname VARCHAR(30) NOT NULL,
	team VARCHAR(30) NOT NULL
	)";
	if ($conn->query($sql) === TRUE) {
	 echo "Table MyChampionship created successfully";
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
	// Check if any option is selected
        if(isset($_POST["subject"]))
        {
			$size = 0;
			foreach ($_POST['subject'] as $subject)
			{
				$size++;
			}
			if($size % 2 == 0){
				// Retrieving each selected option
				foreach ($_POST['subject'] as $subject)
				{
					$sql = "INSERT INTO MyChampionship (chpname, team)
					VALUES ('$chpname', '$subject')";
					if ($conn->multi_query($sql) === TRUE) {
					echo "New records created successfully";
					} else {
					echo "Error: " . $sql . "<br>" . $conn->error;
					}
				}
			}
			else{
				echo "Select an odd number of teams !!";
			}
        }
		else
		{
        echo "Select an option first !!";
		}
	$conn->close();
}
?> 