<?php
require_once 'mysqli_table.php';
require_once "credentials.php";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$stmt = mysqli_stmt_init($conn);

if (mysqli_stmt_prepare($stmt, 'SELECT id, firstname, lastname FROM MyGuests
                                WHERE id = ?')) {
    $query = "2";

    /* bind parameters for markers */
    mysqli_stmt_bind_param($stmt, "s", $query);

    /* execute query */
    mysqli_stmt_execute($stmt);

    /* bind result variables */
    $result = mysqli_stmt_get_result($stmt);

    /* fetch value */
    mysqli_stmt_fetch($stmt);

    /* close statement */
    mysqli_stmt_close($stmt);
}

mysqli_close($conn);
?>
<!DOCTYPE html>
<html>
<head>
  <title>Teste PHP</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h1>Usuários</h1>
  <?php
  if (mysqli_num_rows($result) > 0) {
      echo create_table_mysql($result);
  } else {
      echo "0 results";
  }
  ?>
</div>
</body>
</html>
