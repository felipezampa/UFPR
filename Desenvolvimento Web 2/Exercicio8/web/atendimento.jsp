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
                    <li class="nav-item">F
                        <a class="nav-link" aria-current="page" href="Clientes">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Atendimento">Atendimento</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a class="btn btn-danger mx-5" type="submit" href="Logout">Logout</a>
                </form>
            </div>
        </nav>
        <main class="card shadow-lg mt-5 p-5 container">
            <h1 class="text-center">Cadastrar Atendimento</h1>
            <form method="post" class="mx-5" action="Atendimento?action=new">
                <div class="row my-2">
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" name="nome">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputDescricao">Descrição</label>
                        <input type="text" class="form-control" id="inputDescricao" name="descricao">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputProduto">Produto</label>
                        <input type="text" class="form-control" id="inputProduto" name="produto">
                    </div>
                    <div class="form-group col">
                        <label for="inputData">Data</label>
                        <input type="date" class="form-control" id="inputData" name="data">
                    </div>
                    <div class="form-group col">
                        <label for="inputtipoAtendimento">Tipo Atendimento</label>
                        <input type="text" class="form-control" id="inputtipoAtendimento" name="cep">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputUsuario">Usuario</label>
                        <input type="text" class="form-control" id="inputUsuario" name="usuario">
                    </div>
                    <div class="form-group col-1">
                        <label for="inputCliente">Cliente</label>
                        <input type="text" class="form-control" id="inputCliente" name="cliente">
                    </div>
                    <div class="form-group col">
                        <label for="inputResposta">Resposta</label>
                        <input type="text" class="form-control" id="inputResposta" name="resposta">
                    </div>
                </div>
                <div class="row my-2 text-center">
                    <hr class="my-3">
                    <div>
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a class="btn btn-secondary mx-3" href="Atendimento">Cancelar</a>
                    </div>
                </div>
            </form>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>

