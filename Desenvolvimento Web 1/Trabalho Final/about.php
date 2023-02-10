<?php
  require "db/authenticate.php";
?>

<!DOCTYPE html>
<html>  
<head>
<!-- Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="bootstrap\css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css\index.css">
    <link rel="stylesheet" type="text/css" href="css\about.css">
    <link rel="shortcut icon" href="images/fedlogo.png">
    <title>Sobre a Loja · Federal Instruments</title>
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
          <li><a href="produtos.php" class="nav-link px-2 text-white">Produtos</a></li>
          <li><a href="carrinho.php" class="nav-link px-2 text-white">Carrinho</a></li>
          <li><a href="conta.php" class="nav-link px-2 text-white">Minha Conta</a></li>
          <li><a href="about.php" class="nav-link px-2 text-secondary">Sobre</a></li>
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
<!--- INÍCIO CONTEÚDO --->
  <div class="jumbotron text-center">
  <h1>Sobre Nós</h1>
  <h3>Federal Instruments</h3> 

  <p class="pph">Névoa púrpura por toda a minha cabeça</p>
  <div>
    <li class="ph1">Agora as coisas não parecem mais as mesmas, estou agindo meio engraçado, mas não sei o motivo</li>
    <li class="ph2">Com licença enquanto beijo o céu</li>
    <li class="ph3">Névoa púrpura por todos os lados, não sei se estou subindo ou descendo, estou feliz ou triste?</li> 
    <li class="ph4">O que quer que seja, aquela garota pôs um feitiço em mim</li>
    <li class="ph5">Me ajude, me ajude, oh, eu não sei, a névoa púrpura estava toda em meus olhos</li> 
    <li class="ph6">Não sei se é dia ou noite</li>
    <li class="ph7">Você me confundiu</li> 
    <li class="ph8">Confundiu minha mente</li> 
    <li class="ph9">Se é amanhã ou somente o final dos tempos, quem sabe?</li>
    <li class="ph10">Me ajude, sim, venha saber, me diga, me diga.</li>
  </div>

  <br>
    <div class="jumbotron text-center">
    <h3>Video Institucional</h3> 
    <iframe width="1280" height="720" src="https://www.youtube.com/embed/DqdEoLHXEYE">
    </iframe>
  <br>

  <br>
    <div class="jumbotron text-center">
    <h3>Nossa Equipe</h3> 
  <br>

  <div class="container">
    <div class="row">
      <div class="col-sm-6">
        <img src="images/greg 2.png" class="img-responsive"><br>
        <h3 class="tit">Vithor Modanese</h3>
        <p class="ceo">CEO / Padeiro</p>
      </div>
      <div class="col-sm-6">
        <img src="images/greg 3.png" class="img-responsive"><br>
        <h3 class="tit">Felipe Zampa</h3>
        <p class="ceo">CEO / Sonegador de Imposto</p>
      </div>
    </div>
  </div>

  <h3>Nosso Endereço</h3>
  <div style="text-decoration:none; overflow:hidden;width:100%; height:450px;">
  <div id="gmapcanvas" style="height:100%; width:100%;max-width:100%;">
    <iframe style="height:100%;width:100%;border:0;" frameborder="0" src="https://www.google.com/maps/embed/v1/search?q=Technology+and+Professional+Education+Sector+-+UFPR&key=AIzaSyBFw0Qbyq9zTFTd-tUY6dZWTgaQzuU17R8"></iframe>
  </div>
    <a class="embedded-maphtml" href="https://www.embed-map.com" id="enablemap-info">https://www.embed-map.com</a>
    <style>#gmapcanvas img{max-height:none;max-width:none!important;background:none!important;}</style>
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
    <script src="js/abt.js"></script>
    <script src="js/reg.js"></script>          
</body>
</html>