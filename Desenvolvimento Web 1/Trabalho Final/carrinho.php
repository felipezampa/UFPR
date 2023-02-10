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
    <link rel="shortcut icon" href="images/fedlogo.png">
    <title>Carrinho · Federal Instruments</title>
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
        <li><a href="carrinho.php" class="nav-link px-2 text-secondary">Carrinho</a></li>
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
<div class="shopping-cart">
<div class="px-4 px-lg-0">

  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-0 bg-white rounded shadow-sm mb-5">
          <div class="jumbotron text-center">
            <h1>Seu carrinho</h1>
          </div>
          <div class="container mt-3 d-flex justify-content-around">  
            <table class="table table-striped table-dark">
              <thead>
                <tr>
                  <th scope="col">Id do Item</th>
                  <th scope="col">Nome do Item</th>
                  <th scope="col">Preço</th>
                  <th scope="col">Quantidade</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">7</th>
                  <td>Explorer</td>
                  <td>R$3299,99</td>
                  <td>
                    <select id="inputState" class="form-control">
                      <option selected>1</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                    </select>
                  </td>
                  <td><button href="#" type="submit" class="btn btn-danger">Remover</button></td>
                </tr>
              </tbody>
            </table> 
          </div>
        </div>
      </div>

      <div class="row py-1 p-3 bg-white rounded shadow-sm">
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
            <h5>
              Forma de pagamento
            </h5>
          </div>
          <div class="p-4">
            <p class="font-italic mb-4">Insira sua forma de pagamento abaixar</p>
            <div class="input-group mb-4 border rounded-pill p-2">
              <select id="inputState" class="form-control border-0">
                <option selected>Boleto</option>
                <option>Cartão de Crédito</option>
                <option>Paypal</option>
                <option>Vale transporte</option>
                <option>Figurinha do Ben-10</option>
              </select>              
            </div>
          </div>
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
            <h5>
              Instruções para o vendedor
            </h5>
                  </div>
          <div class="p-4">
            <p class="font-italic mb-4">Se você tiver alguma informação para o vendedor, você pode deixá-la na caixa abaixo</p>
            <textarea name="" cols="30" rows="2" class="form-control"></textarea>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
              <h5>
                Sumário da Compra
              </h5>
          </div>
          <div class="p-4">
            <p class="font-italic mb-4">Frete e custos adicionais são calculados com base nos valores que você inseriu.</p>
            <ul class="list-unstyled mb-4">
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Subtotal do pedido </strong><strong>R$3299,99</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Envio e manipulação (GRÁTIS)</strong><strong>Retirar no SEPT</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Imposto</strong><strong>R$0.00</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                <h5 class="font-weight-bold">R$3299,99</h5>
              </li>
            </ul>
              <div class="comprar">
                <a href="index.php" class="btn btn-warning py-2 btn-block" >Confirmar pedido</a>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--- RODAPÉ --->
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
  <script src="js/carrinho.js"></script>
</body>
</html>