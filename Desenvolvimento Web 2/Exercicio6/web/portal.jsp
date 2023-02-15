<%
    if (session.getAttribute("login") == null) {
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
%>
    <jsp:forward page="index.jsp"/>
<%
    }
%>

<jsp:useBean id="login" class="com.ufpr.tads.web2.beans.LoginBean" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand mx-4" href="#">Portal</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Clientes">Clientes</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a class="btn btn-danger mx-5" type="submit" href="Logout">Logout</a>
                </form>
            </div>
        </nav>
        <main class="card shadow-lg mt-5 p-5 container text-center">
            <h1>Olá ${login.nomeUsuario}, Seja bem vindo!</h1>
            <hr>
            <div class="text-center">
            <a href="Clientes" class="btn btn-primary mx-3">Cadastrar</a>
            <a href="Logout" class="btn btn-secondary">Logout</a>
             </div>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador:  ${configuracao.email}
    </footer>
</html>
