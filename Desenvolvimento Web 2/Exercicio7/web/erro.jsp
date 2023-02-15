<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <main class="container mt-5">
            <h1 class="text-center">Ocorreu um erro!</h1>
            <h3 class="text-danger bg-danger bg-opacity-10">${pageContext.exception.message}</h3>
            <hr>
            <p>
                ${pageContext.out.flush()}
                ${pageContext.exception.printStackTrace(pageContext.response.writer)}
            </p>
            <a href="Clientes" class="btn btn-primary">Voltar</a>
        </main>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
    </body>
</html>
