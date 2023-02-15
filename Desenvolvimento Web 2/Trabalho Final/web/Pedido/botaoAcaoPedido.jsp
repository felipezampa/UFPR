<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${pedido.situacaoPedido == 'EM_ABERTO'}">
        <c:choose>
            <c:when test="${login.funcionario}">
                <button class="btn btn-success js-recolher-button" data-pedido-id="${pedido.id}">Recolher</button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-success js-cancelar-button" data-pedido-id="${pedido.id}">Cancelar</button>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${pedido.situacaoPedido == 'RECOLHIDO' && login.funcionario}">
        <button class="btn btn-success js-confirmar-lavagem-button" data-pedido-id="${pedido.id}">Confirmar lavagem</button>
    </c:when>
    <c:when test="${pedido.situacaoPedido == 'AGUARDANDO_PAGAMENTO' && !login.funcionario}">
        <button class="btn btn-success js-pagar-button" data-pedido-id="${pedido.id}">Pagar</button>
    </c:when>
    <c:when test="${pedido.situacaoPedido == 'PAGO' && login.funcionario}">
        <button class="btn btn-success js-finalizar-button" data-pedido-id="${pedido.id}">Finalizar</button>
    </c:when>
</c:choose>