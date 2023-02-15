<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Exercicio 6 - MVC</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-5">
        <main class="card shadow-lg p-5 m-auto">
            <% 
            if (request.getAttribute("msg") != null) {
                out.println(
                    "<div class=\"text-danger bg-danger bg-opacity-10\"><h1>" 
                    + request.getAttribute("msg")
                    + "</h1></div><hr>"
                );
            }
            %>
            <h1 class="text-center">Login</h1>
            <form action="Login" method="post">
                <label for="login">Login: </label>
                <input type="text" id="login" class="form-control mb-3" name="login">
                <label for="password">Senha: </label>
                <input type="password" id="password" class="form-control" name="password">
                <div class="text-center mt-3">
                    <input type="submit" class="btn btn-primary mt-3" value="Logar"/>
                </div>
            </form>
        </main>
        <footer class="fixed-bottom bg-secondary text-white text-center">
            Em caso de problemas, contactar o Administrador: ${configuracao.email}
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
        </footer>
    </body>
</html>

