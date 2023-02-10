<?php
//---- Credenciais----
$servername = "localhost";
$username = "root";
$db_password = "";
$dbname = "teste";
$table_users = "Users";
//---- Funcoes ----
function connect_db(){
    global $servername, $username, $db_password, $dbname;
    $conn = mysqli_connect($servername, $username, $db_password, $dbname);
  
    if (!$conn) {
        die("Não foi possível conectar: " . mysqli_connect_error());
    }
  
    return($conn);
  }

function disconnect_db($conn){
    mysqli_close($conn);
}  

$error = false;
$success = false;
$name = $email = "";
//---- Teste de resgistro ----
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["password"]) && isset($_POST["confirm_password"])) {

    $conn = connect_db();

    $name = mysqli_real_escape_string($conn,$_POST["name"]);
    $email = mysqli_real_escape_string($conn,$_POST["email"]);
    $password = mysqli_real_escape_string($conn,$_POST["password"]);
    $confirm_password = mysqli_real_escape_string($conn,$_POST["confirm_password"]);

    if ($password == $confirm_password) {
      $password = md5($password);

      $sql = "INSERT INTO $table_users
              (name, email, password) VALUES
              ('$name', '$email', '$password');";

      if(mysqli_query($conn, $sql)){
        $success = true;
      }
      else {
        $error_msg = mysqli_error($conn);
        $error = true;
      }
    }
    else {
      $error_msg = "Senha não confere com a confirmação.";
      $error = true;
    }
    if (strlen($_POST["password"]) <= '6') {
        $passwordErr = "Sua senha deve ter mais que 6 caracteres.";
    }
  }
  else {
    $error_msg = "Por favor, preencha todos os dados.";
    $error = true;
  }
}
//---- Inicia a sessão e atribui variaveis do Db para a sessão
session_start();

if (isset($_SESSION["user_id"]) && isset($_SESSION["user_name"]) && isset($_SESSION["user_email"])) {
  $login = true;
  $user_id = $_SESSION["user_id"];
  $user_name = $_SESSION["user_name"];
  $user_email = $_SESSION["user_email"];
}
else{
  $login = false;
}

?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form name="new_user" action="cadastro.php" method="post">
        <label for="name">Nome</label>
        <input type="text" name="name" required>
        <label for="email">Email</label>
        <input type="text" name="email" required>
        <label for="password">Senha</label>
        <input type="password" name="password" required>
        <label for="confirm_password">Confirmar senha</label>
        <input type="text" name="confirm_password" required>
        <input type="submit" value="Cadastrar">
    </form>
</body>

</html>