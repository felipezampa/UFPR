<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ page import="com.ufpr.tads.web2.enums.SituacaoPedido" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty sessionScope.login}">
        <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request"/>
        <jsp:forward page="/index.jsp" />
    </c:when>
    <c:when test="${sessionScope.login.funcionario == true}">
        <c:set var="msg" value="Essa página é restrita para clientes" scope="request"/>
        <c:set var="errorPath" value="/FuncionarioServlet" scope="request"/>
        <jsp:forward page="/Erro/erro.jsp" />
    </c:when>
</c:choose>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html">
    <title>Index Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="icon" type="image/x-icon" href="<c:url value='/assets/img/favicon.ico'/>">
</head>
<body id="body-pd">
<c:import url="sidebar.jsp"/>
<h1 class="text-center">Bem vindo ${login.nomeUsuario}</h1>
<main class="conteiner-fluid">
    <div class="row justify-content-center">
        <div class="mt-5 col-10">
            <div class="card shadow-lg">
                <div class="card-header py-3 text-center">
                    <h4 class="mb-0">Seus Pedidos</h4>
                </div>
                <div class="card-body overflow-auto ">
                    <div class="form-group col-md-8 mt-2">
                        <label class="mr-sm-2" for="filtro-situacao">Situação</label>
                        <select class="form-select m-2 d-inline js-filtro-situacao" id="filtro-situacao" style="width: auto">
                            <option value="TODAS">Todas</option>
                            <c:forEach var="situacao" items="${situacoesParaFiltrar}">
                                <option value="${situacao}" ${situacao.toString() eq filtroSituacao.toString() ? "selected=\"selected\"" : ""}>${situacao.getDescricao()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <c:import url="../Pedido/tabelaPedidos.jsp"></c:import>
                </div>
                <div class="card-footer text-center mt-2">
                    <c:url var="novoPedidoURL" value="/ClienteServlet" context="${pageContext.request.contextPath}">
                        <c:param name="action" value="novoPedido"/>
                    </c:url>
                    <a class="btn btn-warning" href="${novoPedidoURL}">Novo Pedido</a>
                </div>
            </div>
        </div>
    </div>
</main>
<footer>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</footer>
</body>
</html>
