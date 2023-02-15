<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty sessionScope.login}">
        <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request"/>
        <jsp:forward page="/index.jsp" />
    </c:when>
    <c:when test="${sessionScope.login.funcionario != true}">
        <c:set var="msg" value="Essa página é restrita para funcionários" scope="request"/>
        <c:set var="errorPath" value="/ClienteServlet" scope="request"/>
        <jsp:forward page="/Erro/erro.jsp" />
    </c:when>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>">
        <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0"/>"/>
        <link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
        <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">
        <title>Roupas</title>
    </head>
    <body id="body-pd">
        <div>
            <c:import url="sidebar.jsp"/>
        </div>
        <main class="container-fluid">
            <div class="row mx-5">
                <div class="card shadow-lg my-5">
                    <div class="card-header py-3 text-center">
                        <h4 class="mb-0 text-center">Listar roupas</h4>
                    </div>
                    <div class="card-body overflow-auto px-5">
                        <c:url var="cadastrarRoupaURL" value="/RoupaServlet" context="${pageContext.request.contextPath}">
                            <c:param name="action" value="formNew"/>
                        </c:url>
                        <a href="${cadastrarRoupaURL}" class="btn btn-primary text-center"><i class="fa-solid fa-plus me-2"></i>Cadastrar Roupa</a>
                        <table class="table table-light table-hover mt-3 table-ellipsis js-funcionarios-table px-5">
                            <thead class="table-dark js-funcionario-table-head">
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>Nome</th>
                                    <th>Preço</th>
                                    <th>Prazo</th>
                                    <th class="col-3 text-center">Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${roupas}" var="roupa">
                                    <tr>
                                        <td class="text-center">${roupa.id}</td>
                                        <td>${roupa.nome}</td>  
                                        <td>R$ ${roupa.preco}</td>
                                        <td>${roupa.prazo}</td>
                                        <td class="text-center">
                                            <c:url var="editarRoupaURL" value="/RoupaServlet" context="${pageContext.request.contextPath}">
                                                <c:param name="action" value="formUpdate"/>
                                                <c:param name="id" value="${roupa.id}"/>
                                            </c:url>
                                            <a href="${editarRoupaURL}" class="btn btn-secondary mx-2"><i class="fa-solid fa-pencil me-2"></i>Editar</a>
                                            <c:url var="deletarRoupaURL" value="/RoupaServlet" context="${pageContext.request.contextPath}">
                                                <c:param name="action" value="deletar"/>
                                                <c:param name="id" value="${roupa.id}"/>
                                            </c:url>
                                            <a href="${deletarRoupaURL}" class="btn btn-danger" onclick="return confirm('Você tem certeza que quer deletar a roupa ${roupa.nome}?')">
                                                <i class="fa-solid fa-trash-can me-2"></i>Excluir
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="<c:url value='/assets/js/PedidosController.js'/>"></script>
        </footer> 
    </body>
</html>
