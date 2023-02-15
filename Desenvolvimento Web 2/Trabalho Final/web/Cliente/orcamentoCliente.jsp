<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
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
    <title>Orçamento Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="icon" type="image/x-icon" href="<c:url value='/assets/img/favicon.ico'/>">
</head>

<body id="body-pd">
    <c:import url="sidebar.jsp"/>
    <main>
        <c:import url="../Orcamento/tabelaOrcamentos.jsp"></c:import>
    </main>

    <footer>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="../../js/sidebar.js"></script>
        <script src="../../js/ValidateForm.js"></script>
    </footer>

</body>
</html>