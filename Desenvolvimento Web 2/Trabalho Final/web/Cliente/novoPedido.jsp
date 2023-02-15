<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
    <link rel="shortcut icon" href="<c:url value='/assets/img/icon.png'/>" type="image/x-icon">
</head>

<body id="body-pd">
<div class="js-modal-templates">
    <div class="js-remover-item-modal-container"></div>
</div>
<div>
    <c:import url="sidebar.jsp"/>
</div>
<main>
    <div class="container-fluid js-adicionar-roupa-container pt-5 px-5">
        <div class="row">
            <div class="col">
                <div class="card shadow-lg overflow-auto">
                    <div class="card-header py-3 text-center">
                        <h4 class="mb-0">Adicionar Roupa</h4>
                    </div>
                    <input type="hidden" class="js-pedido-id" name="pedidoId" value="${pedido.id}">
                    <div class="col px-3">
                        <div class="form-group col-md-8 mt-2">
                            <label class="mr-sm-2" for="peca-roupa">Peça de Roupa</label>
                            <select class="form-select mr-sm-2 js-select-roupa" name="roupaId" id="peca-roupa" required>
                                <c:forEach var="roupa" items="${roupas}" >
                                    <option value="${roupa.id}">${roupa.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-4 mt-2">
                            <label for="quantidade">Quantidade</label>
                            <input type="number" class="form-control js-quantidade" name="quantidade" id="quantidade" placeholder="Quantidade" required>
                        </div>
                    </div>
                    <div class="my-3 text-center">
                        <button type="submit" class="btn btn-primary js-adicionar-carrinho">Adicionar ao carrinho</button>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-lg overflow-auto">
                    <div class="card-header py-3 text-center">
                        <h4 class="mb-0">Carrinho</h4>
                    </div>
                    <div class="card-body overflow-auto">
                        <table class="table table-striped table-light table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>Peça de roupa</th>
                                    <th class="text-center">Quantidade</th>
                                    <th class="text-center">Preço</th>
                                    <th class="text-center">Ações</th>
                                </tr>
                            </thead>
                            <tbody class="js-carrinho-table-body">
                                <c:forEach var="item" items="${itensPedido}">
                                    <tr class=".js-item-tr">
                                        <td>${item.roupa.nome}</td>
                                        <td class="text-center">${item.quantidade}</td>
                                        <td class="text-center">${item.quantidade * item.roupa.preco}</td>
                                        <td class="text-center"><button class="btn btn-danger js-excluir-item" data-item-id="${item.id}" data-roupa="${item.roupa.nome}">Excluir</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="text-center">
                            <hr>
                            <c:url var="solicitarOrcamentoURL" value="/PedidoServlet" context="${pageContext.request.contextPath}">
                                <c:param name="action" value="solicitarOrcamento"/>
                                <c:param name="pedidoId" value="${pedido.id}"/>
                            </c:url>
                            <a class="btn btn-lg btn-success js-enviar-pedido">Enviar Pedido</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/assets/js/sidebar.js'/>"></script>
    <script src="<c:url value='/assets/js/RemoverItemModalController.js'/>"></script>
    <script src="<c:url value='/assets/js/NovoPedidoController.js'/>"></script>
</footer>

</body>

</html>