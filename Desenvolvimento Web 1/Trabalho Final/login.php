<?php
require "db/db_functions.php";
require "db/authenticate.php";

$error = false;
$password = $email = "";

if (!$login && $_SERVER["REQUEST_METHOD"] == "POST") {
  if (isset($_POST["email"]) && isset($_POST["password"])) {

    $conn = connect_db();

    $email = mysqli_real_escape_string($conn,$_POST["email"]);
    $password = mysqli_real_escape_string($conn,$_POST["password"]);
    $password = md5($password);

    $sql = "SELECT id,name,email,nasc,password FROM $table WHERE email = '$email'";

    $result = mysqli_query($conn, $sql);
    if($result){
      if (mysqli_num_rows($result) > 0) {
        $user = mysqli_fetch_assoc($result);

        if ($user["password"] == $password) {

          $_SESSION["user_id"] = $user["id"];
          $_SESSION["user_name"] = $user["name"];
          $_SESSION["user_email"] = $user["email"];

          header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/index.php");
          exit();
        }
        else {
          $error_msg = "Senha incorreta!";
          $error = true;
        }
      }
      else{
        $error_msg = "Usuário não encontrado!";
        $error = true;
      }
    }
    else {
      $error_msg = mysqli_error($conn);
      $error = true;
    }
  }
  else {
    $error_msg = "Por favor, preencha todos os dados.";
    $error = true;
  }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
    <title>Login · Federal Instruments</title>
    <link rel="stylesheet" type="text/css" href="bootstrap\css\bootstrap.min.css">
    <link rel="stylesheet" href="css/paginalogin.css">
    <link rel="shortcut icon" href="images/fedlogo.png">
</head>

<body>

<?php if ($login): ?>
    <h3>Você está logado!</h3>
  </body>
  </html>
  <?php exit(); ?>
<?php endif; ?>

<?php if ($error): ?>
  <h3 style="color:red;"><?php echo $error_msg; ?></h3>
<?php endif; ?>
<!--- INÍCIO CONTEÚDO --->
  <div class="container-fluid">
      <div class="row mh-100vh">
          <div class="col-10 col-sm-8 col-md-6 col-lg-6 offset-1 offset-sm-2 offset-md-3 offset-lg-0 align-self-center d-lg-flex align-items-lg-center align-self-lg-stretch bg-white p-5 rounded rounded-lg-0 my-5 my-lg-0" id="login-block">
              <div class="m-auto w-lg-75 w-xl-50">
                  <h2 class="text-info font-weight-light mb-5"><i class="fas fa-hand-rock"></i>&nbsp;Federal Instruments</h2>
                  <form action="login.php" method="post">
                      <div class="form-group">
                        <label for="email" class="text-secondary">E-Mail</label>
                        <input class="form-control" type="text" name="email" value="<?php echo $email; ?>" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,15}$" inputmode="email">
                      </div>
                      <div class="form-group">
                        <label for="password" class="text-secondary">Senha</label>
                        <input class="form-control" type="password" name="password" value="" required>
                      </div>
                      <a href="/index.php"><button class="btn btn-info mt-2" type="submit">Log In</button></a>
                  </form>
                  <p class="mt-3 mb-0"><a class="text-info small" href="esquecer.php">Esqueceu seu e-mail?</a></p>
                  <p class="mt-1 mb-0"><a class="text-info small" href="registrar.php">Não possui conta? Crie uma agora!</a></p>
                  <a href="index.php"><button type="button" class="btn btn-secondary mt-5">Voltar a Página Inicial</button></a>
              </div>
          </div>
          <div class="col-lg-6 d-flex align-items-end"; style="background-image: url(images/a2.png)";>
          </div>
      </div>
  </div>
<!--- FIM CONTEÚDO --->

    <script type="text/javascript" src="bootstrap/css/bootstrap-grid.min.css"></script>
    <script src="js/jquery-3.6.0.js"></script>
</body>

</html>