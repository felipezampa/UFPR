<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.login}">
    <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request"/>
    <jsp:forward page="index.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
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
                        <a class="nav-link" aria-current="page" href="Atendimento">Atendimentos</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a class="btn btn-danger mx-5" type="submit" href="Logout">Logout</a>
                </form>
            </div>
        </nav>
        <main class="card shadow-lg mt-5 p-5 container">
            <h1 class="text-center mb-4">Detalhes Cliente</h1>
            <div class="row mb-2">
                <div class="col">
                    <h3>Nome</h3><hr>
                    <h5>${atendimento.nome}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>Produto</h3><hr>
                    <h5>${atendimento.produto.nome}</h5>
                </div>
                <div class="col">
                    <h3>Descrição</h3><hr>
                    <h5>${atendimento.descricao}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>Tipo de Atendimento</h3><hr>
                    <h5>${atendimento.tipoAtendimento.nome}</h5>
                </div>
                <div class="col">
                    <h3>Data</h3><hr>
                    <h5>${atendimento.data}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>Usuario </h3><hr>
                    <h5>${atendimento.usuario.nome}</h5>
                </div>
                <div class="col">
                    <h3>Cliente </h3><hr>
                    <h5>${atendimento.cliente.nome}</h5>
                </div>
                <div class="col">
                    <h3>Resposta</h3><hr>
                    <h5>${atendimento.resposta}</h5> 
                </div>
            </div>
            <div class="row my-2 text-center">
                <hr class="my-3">
                <div>
                    <a class="btn btn-primary mx-3" href="Atendimento?action=list">Voltar</a>
                </div>
            </div>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>

