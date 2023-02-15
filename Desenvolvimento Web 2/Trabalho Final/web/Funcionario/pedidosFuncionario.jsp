<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:choose>
            <c:when test="${empty sessionScope.login}">
                <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request" />
                <jsp:forward page="/index.jsp" />
            </c:when>
            <c:when test="${sessionScope.login.funcionario != true}">
                <c:set var="msg" value="Essa página é restrita para funcionários" scope="request" />
                <c:set var="errorPath" value="/ClienteServlet" scope="request" />
                <jsp:forward page="/Erro/erro.jsp" />
            </c:when>
        </c:choose>
        <!DOCTYPE html>
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <link rel="stylesheet"
                href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
            <link rel="stylesheet" href="<c:url value='/assets/css/pedidos.css'/>">
            <link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
            <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">
            <title>Pedidos</title>
        </head>
        <body id="body-pd">
            <div>
                <c:import url="sidebar.jsp" />
            </div>
            <main class="container-fluid">
                <div class="row mx-5">
                    <div class="card shadow-lg my-5">
                        <div class="card-header py-3 text-center">
                            <h4 class="mb-0">Todos os pedidos</h4>
                            <div class="container-form">
                                <span>Escolha a opção de filtro</span><br>

                                <c:url var="pedidosFuncionarioURL" value="/FuncionarioServlet"
                                    context="${pageContext.request.contextPath}">
                                    <c:param name="action" value="pedidos" />
                                </c:url>
                                <form class="data-form" method="post" action="${pedidosFuncionarioURL}">

                                    <div class="radio-form">
                                        <div>
                                            <input id="filtro-dias" class="input-dias" type="radio" name="filtro"
                                                value="dias" />
                                            <label for="dias">Intervalos de data</label>
                                        </div>
                                        <div>
                                            <input class="input-hoje" type="radio" name="filtro" value="hoje" />
                                            <label> Dia de hoje </label>
                                        </div>
                                        <div>
                                            <input class="input-todos" type="radio" name="filtro" value="todos" />
                                            <label>Todos os pedidos</label>
                                        </div>
                                    </div>
                                    <div class="form-flex">
                                        <div class="div-datas" id="div-dias" style="display: none;">
                                            <input placeholder="Data incio" type="text" name="dataInicio"
                                                onfocus="(this.type = 'date')" onblur="if (this.value == '') {
                                                this.type = 'text';
                                            }" />
                                            <input placeholder="Data final" type="text" name="dataFim"
                                                onfocus="(this.type = 'date')" onblur="if (this.value == '') {
                                                this.type = 'text';
                                            }" />
                                        </div>
                                        <input class="data-button" type="submit" value="Filtrar" />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <c:import url="../Pedido/tabelaPedidos.jsp"></c:import>
                        <div class="card-footer text-center mt-2">
                            <button class="btn btn-warning">Ver orçamentos</button>
                        </div>
                    </div>
                </div>
            </main>
            <footer>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
                <script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script src="<c:url value='/assets/js/PedidosController.js'/>"></script>
            </footer>

        </body>
        </html>