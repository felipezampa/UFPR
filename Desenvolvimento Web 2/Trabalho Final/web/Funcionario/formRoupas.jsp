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
        <title>Form Roupa</title>
    </head>
    <body id="body-pd">
        <div>
            <c:import url="sidebar.jsp"/>
        </div>
        <main class="container-fluid">
            <div class="row mx-5">
                <div class="card shadow-lg my-5">
                    <div class="card-header py-3">
                        <h4 class="mb-0 text-center">
                            <c:choose>
                                <c:when test="${empty roupa}">
                                    <c:out value="Cadastrar Roupa"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="Alterar Roupa"/>
                                </c:otherwise>
                            </c:choose>
                        </h4>
                        <c:url var="salvarRoupaURL" value="/RoupaServlet" context="${pageContext.request.contextPath}">
                            <c:choose>
                                <c:when test="${empty roupa}">
                                    <c:param name="action" value="inserir" />
                                </c:when>
                                <c:otherwise>
                                    <c:param name="action" value="atualizar" />
                                    <c:param name="id" value="${roupa.id}" />
                                </c:otherwise>
                            </c:choose>
                        </c:url>
                        <hr>
                        <form action="${salvarRoupaURL}" method="post">
                            <div class="form-group mt-3">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" id="nome" class="form-control" value="${roupa.nome}"/>
                            </div>
                            <div class="form-group mt-3">
                                <label for="preco">Preço</label>
                                <input type="number" name="preco" id="preco" class="form-control" value="${roupa.preco}"/>
                            </div>
                            <div class="form-group mt-3">
                                <label for="prazo">Prazo</label>
                                <input type="time" name="prazo" id="prazo"  step="1" class="form-control" value="${roupa.prazo}" />
                            </div>
                            <div class="d-flex justify-content-center mt-4">
                                <input type="submit" class="btn btn-success mx-3" value="Salvar"/>
                                <c:url var="voltarURL" value="/RoupaServlet" context="${pageContext.request.contextPath}"/>
                                <a href="${voltarURL}" class="btn btn-secondary">Voltar</a>
                            </div>
                        </form>
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
