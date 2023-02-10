<?php
require "db/db_functions.php";
require "db/authenticate.php";

$error = false;
$success = false;
$name = $email = $nasc = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["nasc"]) && isset($_POST["password"]) && isset($_POST["confirm_password"])) {

    $conn = connect_db();

    $name = mysqli_real_escape_string($conn,$_POST["name"]);
    $email = mysqli_real_escape_string($conn,$_POST["email"]);
    $nasc = mysqli_real_escape_string($conn,$_POST["nasc"]);
    $password = mysqli_real_escape_string($conn,$_POST["password"]);
    $confirm_password = mysqli_real_escape_string($conn,$_POST["confirm_password"]);

    if ($password == $confirm_password) {
      $password = md5($password);

      $sql = "INSERT INTO $table
              (name, email, nasc, password) VALUES
              ('$name', '$email', '$nasc', '$password');";

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

<!-- Javascript Form -->
    <script src="js/check_form.js"></script>

<!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="bootstrap\css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" href="text/css" href="css/registrar.css" >
    <link rel="shortcut icon" href="images/fedlogo.png">
    <title>Criar Conta · Federal Instruments</title>
</head>

<body>
<!--- INÍCIO CABEÇALHO --->
  <div class="b-example-divider"></div>

  <header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="index.php" class="nav-link px-2 text-white">Home</a></li>
          <li><a href="produtos.php" class="nav-link px-2 text-white">Produtos</a></li>
          <li><a href="carrinho.php" class="nav-link px-2 text-white">Carrinho</a></li>
          <li><a href="conta.php" class="nav-link px-2 text-white">Minha Conta</a></li>
          <li><a href="about.php" class="nav-link px-2 text-white">Sobre</a></li>
        </ul>
        
        <?php if ($login): ?>
          <div class="text-end">
          <a href="conta.php">
            <button type="button" class="btn btn-outline-light me-2">Minha Conta</button>
          </a>
          <a href="logout.php">
            <button type="button" class="btn btn-warning">Sair</button>
          </a>
        </div>

        <?php else: ?>
          <div class="text-end">
          <a href="login.php">
            <button type="button" class="btn btn-outline-light me-2">Entrar</button>
          </a>
          <a href="registrar.php">
            <button type="button" class="btn btn-warning">Criar Conta</button>
          </a>
          </div>
        <?php endif; ?>                
      </div>
    </div>
  </header>
<!--- FIM CABEÇALHO --->
<!--- INÍCIO CONTEÚDO --->
  <div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-5">
          <div class="m-auto w-lg-75 w-xl-75">
          <h1 class="text-center">Criar Conta</h1>
            <div>
                  <h2>Formulário:</h2>
                <?php if ($success): ?>
                  <h3 style="color:green;">Usuário criado com sucesso!</h3>
                <?php endif; ?>

                <?php if ($error): ?>
                  <h3 style="color:red;"><?php echo $error_msg; ?></h3>
                <?php endif; ?>

                <p><span class="error">* Campo Requerido</span></p>
                <form method="post" enctype="multipart/form-data" id="form-test" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">  

                  <label for="name"> *Nome: </label><br>
                  <input required type="text" placeholder="Seu nome" class="form-control" name="name" value= "<?php echo $name; ?>" required><br><br>
                  
                  <label for="email"> *E-Mail: </label><br>
                  <input required type="text" placeholder="email@dominio.com" class="form-control" name="email" value= "<?php echo $email; ?>" required><br><br>

                  <label for="nasc"> *Data de Nascimento: </label><br>
                  <input required type="date" class="form-control" name="nasc" value= "<?php echo $nasc; ?>" required><br><br>

                  <label for="password"> *Senha: </label><br>
                  <input type="password" placeholder="Sua Senha" class="form-control" name="password" value="" required><br><br>
                  
                  <label for="confirm_password"> *Confirme a Senha:</label><br>
                  <input type="password" placeholder="Confirme a Senha"  class="form-control" name="confirm_password" value="" required><br><br>
                  
                  <input type="submit" name="submit" value="Registrar" class="btn btn-danger"> 
                </form>
            </div>
          </div>
        </div>
    </div>
  </div>                
<!--- FIM CONTEÚDO --->

<!--- INÍCIO RODAPÉ --->
  <footer class="container py-5">
    <div class="row">
      <div class="col-12 col-md">
  
        <small class="d-block mb-3 text-muted"><?php include 'rodape.php';?></small>
      </div>
      <div class="col-6 col-md">
        <h5>Funcionalidades</h5>
        <ul class="list-unstyled text-small">
        <li> <a class="text-muted" href="#">Coisas legais</a></li>
        <li> <a class="text-muted" href="#">Recurso aleatório</a></li>
        <li> <a class="text-muted" href="#">Recurso de equipe</a></li>
        <li> <a class="text-muted" href="#">Coisas para desenvolvedores</a></li>
        <li> <a class="text-muted" href="#">Outro</a></li>
        <li> <a class="text-muted" href="#">Última vez</a></li>
        </ul>
      </div>
      <div class="col-6 col-md">
        <h5>Recursos</h5>
        <ul class="list-unstyled text-small">
        <li> <a class="text-muted" href="#">Recurso</a></li>
        <li> <a class="text-muted" href="#">Nome do recurso</a></li>
        <li> <a class="text-muted" href="#">Outro recurso</a></li>
        <li> <a class="text-muted" href="#">Recurso final</a></li>
        </ul>
      </div>
      <div class="col-6 col-md">
        <h5>Sobre</h5>
        <ul class="list-unstyled text-small">
        <li> <a class="text-muted" href="#">Equipe</a></li>
        <li> <a class="text-muted" href="#">Locais</a></li>
        <li> <a class="text-muted" href="#">Privacidade</a></li>
        <li> <a class="text-muted" href="#">Termos</a></li>
        </ul>
      </div>
    </div>
  </footer>

<!--- FIM RODAPÉ --->
  <script type="text/javascript" src="bootstrap\js\bootstrap.min.js"></script>
  <script src="js/jquery-3.6.0.js"></script>
  <script src="js/reg.js"></script>
</body>

</html>