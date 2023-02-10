<?php
  $servername = "db"; //"localhost";
  $username = "root";
  $password = "web1";
  $dbname = "ds120_2019_1_comments";

  // Create connection
  $conn = mysqli_connect($servername, $username, $password,$dbname);
  // Check connection
  if (!$conn) {
      die("Connection failed: " . mysqli_connect_error());
  }
?>
