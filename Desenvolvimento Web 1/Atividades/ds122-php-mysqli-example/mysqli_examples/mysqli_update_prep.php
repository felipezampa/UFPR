<?php
require_once "credentials.php";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$stmt = mysqli_stmt_init($conn);

if (mysqli_stmt_prepare($stmt, "UPDATE MyGuests SET lastname='Kutzke' WHERE id=?")) {
  $query = $_GET["id"];

  mysqli_stmt_bind_param($stmt, "s", $query);

  if (mysqli_stmt_execute($stmt)) {
      echo "Record updated successfully";
  } else {
      echo "Error updating record: " . mysqli_error($conn);
  }

  mysqli_stmt_close($stmt);
}
mysqli_close($conn);
?>
