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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" rel="stylesheet">
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
                        <a class="nav-link" href="Clientes?action=formNew">Novo Cliente</a>
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
            <div><a href="Atendimento?action=formNew"" class="btn btn-primary mb-2">Novo Atendimento</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Resposta</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${atendimentos}" var="atendimento">
                        <tr>
                            <td>${atendimento.nome}</td>
                            <td>${atendimento.descricao}</td>
                            <td>${atendimento.resposta}</td>
                            <td class="col-3">
                                <a href="Clientes?action=show&id=${atendimento.id}" class="btn btn-success"><i class="fa-solid fa-eye"></i> Ver</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>
