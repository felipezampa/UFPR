<?php
  require "db/authenticate.php";
?> 

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="bootstrap\css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css\index.css">
    <link rel="shortcut icon" href="images/fedlogo.png">
    <title>Início · Federal Instruments</title>
</head>

<body>
<!--- INÍCIO CABEÇALHO--->
  <div class="b-example-divider"></div>

  <header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="index.php" class="nav-link px-2 text-secondary">Home</a></li>
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
<!--- FIM CABEÇALHO--->

<!-- Área do cabeçalho da imagem de página inteira -->
  <div id="top" class="header">
      <div class="vert-text">
          <h1>Federal Instruments</h1>
          <h3> 
              <em>Pra</em> comprar aqui tem que comer no R.U.</h3>
              <a href="produtos.php">
              <button class="btn btn-danger">Ver Catálogo</button>
            </a>
      </div>
  </div>

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
  <script src="js/pg.js"></script>
</body>

</html>