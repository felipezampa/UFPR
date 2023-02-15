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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0"/>
        <link rel="stylesheet" href="<c:url value='/assets/css/pedidos.css'/>">
        <link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
        <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">
    </head>
    <body id="body-pd">
        <div>
            <c:import url="sidebar.jsp"/>
        </div>
        <main class="container-fluid">
            <div class="row">
                <div class="col-8 mx-3  mt-5">
                    <div class="card shadow-lg">
                        <div class="card-header">
                            <h4 class="text-center">Pedidos em aberto</h4>
                        </div>
                        <div class="card-body overflow-auto ">
                            <c:import url="../Pedido/tabelaPedidos.jsp"></c:import>
                            </div>
                            <div class="card-footer text-center mt-2">
                            <c:url var="pedidosURL" value="/FuncionarioServlet" context="${pageContext.request.contextPath}">
                                <c:param name="action" value="pedidos"/>
                            </c:url>
                            <a class="btn btn-primary mx-3" href="${pedidosURL}">Ver todos os pedidos</a>
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