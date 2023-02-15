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
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Clientes?action=formNew">Novo Cliente</a>
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
                    <h5>${cliente.nome}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <h3>Email</h3><hr>
                <h5>${cliente.email}</h5>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>Cidade</h3><hr>
                    <h5>${cliente.cidade}</h5>
                </div>
                <div class="col">
                    <h3>UF</h3><hr>
                    <h5>${cliente.uf}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>CPF</h3><hr>
                    <h5>${cliente.cpf}</h5>
                </div>
                <div class="col">
                    <h3>Data</h3><hr>
                    <h5>${cliente.data}</h5>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col">
                    <h3>Rua</h3><hr>
                    <h5>${cliente.rua}</h5>
                </div>
                <div class="col">
                    <h3>Numero</h3><hr>
                    <h5>${cliente.numero}</h5>
                </div>
                <div class="col">
                    <h3>CEP</h3><hr>
                    <h5>${cliente.cep}</h5> 
                </div>
            </div>
            <div class="row my-2 text-center">
                <hr class="my-3">
                <div>
                    <a class="btn btn-primary mx-3" href="Clientes?action=list">Voltar</a>
                </div>
            </div>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>

