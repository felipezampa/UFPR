<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>

<%
    if (session.getAttribute("login") == null) {
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
%>
<jsp:forward page="index.jsp"/>
<%
    }
%>
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
        <main class="card shadow-lg mt-5 p-5 container">
            <h1 class="text-center">Alterar Cliente</h1>
            <form method="post" class="mx-5" action="Clientes?action=update">
                <input type="hidden" value="${cliente.id}" name="id">
                <div class="row my-2">
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" name="nome" value="${cliente.nome}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputEmail">Email</label>
                        <input type="email" class="form-control" id="inputEmail" name="email"  value="${cliente.email}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputCPF">CPF</label>
                        <input type="text" class="form-control" id="inputCPF" name="cpf"  value="${cliente.cpf}">
                    </div>
                    <div class="form-group col">
                        <label for="inputData">Data</label>
                        <input type="date" class="form-control" id="inputData" name="data"  value="${cliente.data}">
                    </div> 
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputRua">Rua</label>
                        <input type="text" class="form-control" id="inputRua" name="rua"  value="${cliente.rua}">
                    </div>
                    <div class="form-group col-1">
                        <label for="inputNum">N°</label>
                        <input type="text" class="form-control" id="inputNum" name="numero"  value="${cliente.numero}">
                    </div>
                    <div class="form-group col">
                        <label for="inputCEP">CEP</label>
                        <input type="text" class="form-control" id="inputCEP" name="cep" value="${cliente.cep}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputCidade">Cidade</label>
                        <input type="text" class="form-control" id="inputCidade" name="cidade" value="${cliente.cidade}">
                    </div>
                    <div class="form-group col">
                        <label for="inputUF">UF</label>
                        <input type="text" class="form-control" id="inputUF" name="uf" value="${cliente.uf}">
                    </div>
                </div>
                <div class="row my-2 text-center">
                    <hr class="my-3">
                    <div>
                        <button type="submit" class="btn btn-primary">Alterar</button>
                        <a class="btn btn-secondary mx-3" href="Clientes">Cancelar</a>
                    </div>
                </div>
            </form>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>

