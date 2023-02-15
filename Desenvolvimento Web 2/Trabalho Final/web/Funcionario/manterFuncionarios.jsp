<%@page import="com.ufpr.tads.web2.beans.Usuario" %>
<%@page import="com.ufpr.tads.web2.beans.LoginBean" %>
<%@page import="java.util.List" %>
<%@page import="com.ufpr.tads.web2.facade.UsuarioFacade" %>
<%@page import="jakarta.jms.Connection" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty sessionScope.login}">
        <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema"
               scope="request" />
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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
        <link rel="stylesheet" href="<c:url value='/assets/css/sidebar.css'/>">
        <link rel="stylesheet" href="<c:url value='/assets/css/funcionarios.css'/>">
        <link rel="shortcut icon" href="<c:url value= '/assets/img/icon.png'/>"
              type="image/x-icon">
    </head>

    <body id="body-pd">
        <div>
            <c:url var="deletarFuncionarioURL" value="/FuncionarioServlet"
                   context="${pageContext.request.contextPath}">
                <c:param name="action" value="deletar" />
            </c:url>
            <div id="container-modal" class="js-modal-templates">
                <div class="js-remover-usuario-modal-container">
                    <div class="modal-excluir-funcionario" tabindex="-1">
                        <form action="${deletarFuncionarioURL}" method="POST">
                            <input id="excluir-action" type="hidden" class="js-usuario-id" name="id" />
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Remover Funcionário</h5>
                                        <button type="button" class="btn-close js-cancel-button" data-bs-dismiss="modal" aria-label="Cancelar"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Você tem certeza que deseja remover o(a) funcionário(a) <span class="js-usuario-nome">${nome}</span>?</p>
                                        <p>Esta ação não poderá ser desfeita.</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn js-cancel-button btn_cancelar" data-bs-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn js-confirm-button">Confirmar</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <c:import url="sidebar.jsp" />
        </div>
        <main class="container-fluid">
            <div class="row">
                <div class="col-8 mx-3  mt-5">
                    <div class="card shadow-lg mb-4">
                        <div class="card-header py-3 text-center">
                            <h5 class="mb-0">Gerenciar Funcionários</h5>
                        </div>
                        <div class="card-body overflow-auto">
                            <table
                                class="table table-light table-hover mb-0 table-ellipsis js-funcionarios-table px-4">

                                <!-- Conteúdo preenchido pelo funcionarioController.js -->
                                <thead class="table-dark js-funcionario-table-head">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>
                                        <th>Email</th>
                                        <th>Data de Nascimento</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody class="js-funcionario-table-body">
                                </tbody>
                                <tfoot>

                                    <c:forEach items="${funcionario}" var="func">
                                        <tr>
                                            <td class="usuario">${func.id}</td>
                                            <td class="usuario">${func.nome}</td>
                                            <td class="usuario">${func.login}</td>
                                            <td class="usuario">${func.dataNascimento}</td>
                                            <td>
                                                <div>
                                                    <button id="${func.id}"
                                                            class="btn btn-primary js-editar-usuario"
                                                            onclick="mostrar('${func.id}')">Editar</button>
                                                    <button 
                                                        class="btn btn-danger js-excluir-usuario" onclick="chamaModal('${func.id}', '${func.nome}')" >Excluir</button></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <c:url var="criarFuncionarioURL" value="/FuncionarioServlet"
                       context="${pageContext.request.contextPath}">
                    <c:param name="action" value="salvarFuncionario" />
                </c:url>
                <div class="col-md-3 mb-4 mt-5">
                    <div class="card shadow-lg mb-4">
                        <div class="card-header py-3 text-center">
                            <h5 class="mb-0 js-card-title">Novo Funcionário</h5>
                        </div>
                        <div class="card-body">
                            <form class="js-funcionario-form" method="post"
                                  action="${criarFuncionarioURL}">
                                <input type="hidden" value="" name="atualizar" id="edidar-action" />
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="detalhe-nome">Nome
                                        completo</label>
                                    <input name="nome" type="text" id="detalhe-nome"
                                           class="form-control" placeholder="João da Silva" />
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-outline mb-4">

                                    <label class="form-label" for="detalhe-email">Email</label>
                                    <input name="email" type="text" class="form-control"
                                           id="detalhe-email" placeholder="login@ufpr.br"/>
                                    <div class="invalid-feedback"></div>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label">Data de Nascimento</label>
                                    <input class="form-control" placeholder="Data de Nascimento"
                                           type="text" name="dataNascimento" id="detalhe_data"
                                           onfocus="(this.type = 'date')" onblur="if (this.value == '') {
                                                       this.type = 'text'
                                                   }"">
                                    <div class=" invalid-feedback">
                                    </div>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <input type="submit"
                                                   class="btn btn-success form-control js-button-usuario-button"
                                                   value="Criar" />
                                        </div>
                                        <div class="col">
                                            <input type="reset"
                                                   class="btn btn-danger form-control js-limpar-formulario-button"
                                                   value="Limpar" />

                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
            <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="<c:url value='assets/js/RemoverUsuarioModalController.js'/>"></script>
            <script src="<c:url value='assets/js/UsuariosController.js'/>"></script>
            <script src="<c:url value='assets/js/FuncionariosController.js'/>"></script>

            <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        </footer>

    </body>

</html>