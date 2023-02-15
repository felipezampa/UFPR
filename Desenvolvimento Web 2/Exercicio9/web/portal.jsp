<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.login}">
    <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request"/>
    <jsp:forward page="index.jsp"/>
</c:if>

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
                <a class="navbar-brand mx-4" href="portal.jsp">Portal</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Clientes">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Atendimento">Atendimentos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Atendimento?action=formNew">Novo Atendimento</a>
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
            <hr>
            <h1 class="mt-5">Relatórios</h1>
            <div class="text-center mt-2">
                <a href="GeradorRelatorio?action=clientes" class="btn btn-success mx-3">Clientes</a>
                <a href="GeradorRelatorio?action=datas" class="btn btn-success">Atendimentos por Data</a>
                <a href="GeradorRelatorio?action=resolvidos" class="btn btn-success mx-3">Atendimentos Resolvidos</a>
                <a href="GeradorRelatorio?action=tipo" class="btn btn-success">Atendimentos de um Tipo</a>
            </div>

        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador:  ${configuracao.email}
    </footer>
</html>
