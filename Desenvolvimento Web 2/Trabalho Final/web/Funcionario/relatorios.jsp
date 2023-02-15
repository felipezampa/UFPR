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
        <link rel="stylesheet" href="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>">
        <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0"/>"/>
        <link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
    </head>
    <body id="body-pd">
        <div>
            <c:import url="sidebar.jsp"/>
        </div>
        <main class="container-fluid">
            <div class="row">
                <div class="col-12 me-5 pe-5  mt-5">

                    <div class="row">
                        <div class="col card me-2">               
                            <c:url var="relatorioReceitasURL" value="/RelatorioServlet" context="${pageContext.request.contextPath}">
                                <c:param name="action" value="receitas"/>
                            </c:url>
                            <form action="${relatorioReceitasURL}" method="POST">
                                <div class="card-body">
                                    <h4 class="text-center">Relatório de Receitas</h4>
                                    <label for="dataInicio">Data Início</label>
                                    <input type="date" class="form-control" name="dataInicio">
                                    <label for="dataFim mt-2">Data Fim</label>
                                    <input type="date" class="form-control" name="dataFim">
                                </div>
                                <div class="d-flex justify-content-center my-3 card-footer">
                                    <button type="submit" class="btn btn-success "><i class="fa fa-download "></i> Gerar PDF</button>
                                </div>
                            </form>
                        </div>
                        <div class="col card mx-2">
                            <div class="card-body">
                                <h4 class="text-center">Relatório de Clientes</h4>
                                <c:url var="relatorioClientesURL" value="/RelatorioServlet" context="${pageContext.request.contextPath}">
                                    <c:param name="action" value="clientes"/>
                                </c:url>
                            </div>
                            <div class="d-flex justify-content-center my-3 card-footer">
                                <a class="btn btn-success" href="${relatorioClientesURL}">
                                    <i class="fa fa-download "></i> Gerar PDF
                                </a>
                            </div>

                        </div>
                        <div class="col card mx-2">
                            <div class="card-body">
                                <h4 class="text-center">Relatório de Clientes Fiéis</h4>
                                <c:url var="relatorioClientesFieisURL" value="/RelatorioServlet" context="${pageContext.request.contextPath}">
                                    <c:param name="action" value="clientesFieis"/>
                                </c:url>
                            </div>
                            <div class="d-flex justify-content-center my-3 card-footer">
                                <a class="btn btn-success" href="${relatorioClientesFieisURL}">
                                    <i class="fa fa-download "></i> Gerar PDF
                                </a>
                            </div>
                        </div>
                        <div class="col card ms-2">
                            <c:url var="relatorioPedidosURL" value="/RelatorioServlet" context="${pageContext.request.contextPath}">
                                <c:param name="action" value="pedidos"/>
                            </c:url>
                            <form action="${relatorioPedidosURL}" method="POST">
                                <div class="card-body">
                                    <h4 class="text-center">Relatório de Pedidos</h4>
                                    <label for="dataInicio">Data Início</label>
                                    <input type="date" class="form-control" name="dataInicio">
                                    <label for="dataFim mt-2">Data Fim</label>
                                    <input type="date" class="form-control" name="dataFim">
                                </div>
                                <div class="d-flex justify-content-center my-3 card-footer">
                                    <button type="submit" class="btn btn-success "><i class="fa fa-download "></i> Gerar PDF</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card-footer text-center mt-5">
                        <c:url var="portalFuncionarioURL" value="/FuncionarioServlet" context="${pageContext.request.contextPath}"/>
                        <a class="btn btn-primary mx-3" href="${portalFuncionarioURL}">Voltar ao portal</a>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <script src="<c:url value="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"/>"></script>
            <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"/>"></script>
            <script src="<c:url value="https://code.jquery.com/jquery-3.6.0.min.js"/>"></script>
        </footer>

    </body>
</html>