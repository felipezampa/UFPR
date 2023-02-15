<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-light table-hover js-pedidos-table">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Data de solicitação</th>
            <th>Preço</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${pedidos}" var="pedido">
            <tr class="${pedido.situacaoPedido.getClasse()}">
                <td>${pedido.id}</td>
                <td>${pedido.dataPedido}</td>
                <td>${pedido.valorPedido}</td>
                <td>${pedido.situacaoPedido.getDescricao()}</td>
                <td><%@include file="botaoAcaoPedido.jsp" %></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<c:url value='/assets/js/PedidosController.js'/>"></script>