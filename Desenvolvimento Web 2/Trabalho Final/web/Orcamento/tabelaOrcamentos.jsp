<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid pt-5 px-5">
    <div class="row">
        <div class="col">
            <div class="row mt-5">
                <div class="card shadow-lg">
                    <div class="card-header py-3 text-center">
                        <h4 class="mb-0">Orçamentos em Aberto</h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-light table-hover js-orcamentos-table">
                            <thead class="table-dark">
                                <tr>
                                    <th class="text-center">Número</th>
                                    <th class="text-center">Valor</th>
                                    <th class="text-center">Prazo</th>
                                    <th class="text-center">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orcamentos}" var="orcamento">
                                <tr class="${orcamento.situacaoPedido.getClasse()}">
                                    <td class="text-center">${orcamento.id}</td>
                                    <td class="text-center">${orcamento.valorPedido}</td>
                                    <td class="text-center">${orcamento.getPrazoPedido()}</td>
                                    <td class="text-center">
                                        <button class="btn btn-success js-aprovar-orcamento" data-pedido-id="${orcamento.id}">Aprovar</button>
                                        <button class="btn btn-danger js-rejeitar-orcamento" data-pedido-id="${orcamento.id}">Rejeitar</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/assets/js/OrcamentoController.js'/>"></script>
