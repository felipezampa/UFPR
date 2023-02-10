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
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/products.css">
    <link rel="shortcut icon" href="images/fedlogo.png">
    <title>Catálogo · Federal Instruments</title>
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
          <li><a href="index.php" class="nav-link px-2 text-white">Home</a></li>
          <li><a href="produtos.php" class="nav-link px-2 text-secondary">Produtos</a></li>
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

<!--- INÍCIO CONTEÚDO--->
  <div class="jumbotron text-center">
    <h1>Catálogo</h1>
  </div>
  <div class="container mt-3 d-flex justify-content-around">
    <div class="row text-center py">      
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post">
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/jazzmaster.png">
            <div class="card-body text-center">
                <h5 class="card-title">Jazzmaster</h5>
                <p class="card-text">R$3999,99</p>
                  <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                  <input type='hidden' name='id' value='1'>
            </div>
          </div>
        </form>
      </div>      
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/stratocaster.png">
            <div class="card-body text-center">
              <h5 class="card-title">Stratocaster</h5>
              <p class="card-text">R$3699,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='2'>
            </div>
          </div>
        </form>
      </div>     
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/telecaster.png">
            <div class="card-body text-center">
              <h5 class="card-title">Telecaster</h5>
              <p class="card-text">R$2999,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
              <input type='hidden' name='id' value='3'> 
            </div>
          </div>
        </form>
      </div>           
    </div>
  </div>

  <div class="container mt-4 d-flex justify-content-around">
    <div class="row">
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/lespaul.png">
            <div class="card-body text-center">
              <h5 class="card-title">Les Paul</h5>
              <p class="card-text">R$4599,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='4'> 
            </div>
          </div>
        </form>
      </div>     
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/es335.png">
            <div class="card-body text-center">
              <h5 class="card-title">ES-335</h5>
              <p class="card-text">R$6899,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='5'> 
            </div>
          </div>
        </form>
      </div>     
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/sg.png">
            <div class="card-body text-center">
              <h5 class="card-title">SG</h5>
              <p class="card-text">R$5799,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='6'> 
            </div>
          </div>
        </form>
      </div>            
    </div>
  </div>

  <div class="container mt-4 d-flex justify-content-around">
    <div class="row">
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/explorer.png">
            <div class="card-body text-center">
              <h5 class="card-title">Explorer</h5>
              <p class="card-text">R$3299,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='7'> 
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/jazzbass.png">
            <div class="card-body text-center">
              <h5 class="card-title">Jazz Bass</h5>
              <p class="card-text">R$3199,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='8'> 
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/precisionbass.png">
            <div class="card-body text-center">
              <h5 class="card-title">Precision Bass</h5>
              <p class="card-text">R$3299,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='9'> 
            </div>
          </div>
        </form>
      </div>  
    </div>
  </div>

  <div class="container mt-4 d-flex justify-content-around">
    <div class="row">
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/whitefalcon.png">
            <div class="card-body text-center">
              <h5 class="card-title">White Falcon</h5>
              <p class="card-text">R$6299,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='10'> 
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/violao.png">
            <div class="card-body text-center">
              <h5 class="card-title">Violão</h5>
              <p class="card-text">R$2299,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='11'> 
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-4 col-sm-6 my-3 my-md-0">
        <form action="carrinho.php" method="post"> 
          <div class="card shadow" style="width: 20rem;">
            <img class="card-img-top" src="images/violaonylon.png">
            <div class="card-body text-center">
              <h5 class="card-title">Violão Nylon</h5>
              <p class="card-text">R$1299,99</p>
                <button type="submit" name="comprar" class="btn btn-dark">Comprar</button>
                <input type='hidden' name='id' value='12'> 
            </div>
          </div>
        </form>
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

  <script type="text/javascript" src="bootstrap/css/bootstrap-grid.min.css"></script>
  <script src="js/jquery-3.6.0.js"></script>
  <script src="js/reg.js"></script>
</body>

</html>