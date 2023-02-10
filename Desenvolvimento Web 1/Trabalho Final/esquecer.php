<!DOCTYPE html>
<html lang="en">
<head>
<!-- Tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="bootstrap\css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/paginalogin.css">
    <title>Recuperar Conta · Federal Instruments</title>
    <link rel="shortcut icon" href="images/fedlogo.png">
</head>

<body>
<div class="container-fluid">
        <div class="row mh-100vh">
            <div class="col-10 col-sm-8 col-md-6 col-lg-6 offset-1 offset-sm-2 offset-md-3 offset-lg-0 align-self-center d-lg-flex align-items-lg-center align-self-lg-stretch bg-white p-5 rounded rounded-lg-0 my-5 my-lg-0" id="login-block">
                <div class="m-auto w-lg-75 w-xl-50">
                    <h2 class="text-info font-weight-light mb-3"><i class="fas fa-hand-rock"></i>&nbsp;Federal Instruments</h2>
                    <form>
                     <p class="font-weight-normal d-flex p-2">Em caso de esquecimento de e-mail é necessário que você digite o seu e-mail onde será enviado uma mensagem com as intruções para recuperar a sua conta.</p>
                     <div class="form-group"><label class="text-secondary">E-Mail</label><input class="form-control" name="email" id="email" required="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,15}$" inputmode="email"></div>

                    </form>
                    <a class="recuperar">
                        <input class="btn btn-info mt-3 align-center" type="button" value="Recuperar a sua Conta">
                    </a>
                    <p class="mt-3 mb-0"><a class="text-info small" href="login.php">Voltar para login</a></p>
                </div>
            </div>
            <div class="col-lg-6 d-flex align-items-end"; style="background-image: url(images/a2.png)";>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="bootstrap/css/bootstrap-grid.min.css"></script>
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/esquecer.js"></script>
</body>

</html>