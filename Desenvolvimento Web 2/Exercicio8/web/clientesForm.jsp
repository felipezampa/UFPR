<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.login}">
    <c:set var="msg" value="Usuário deve se autenticar para acessar o sistema" scope="request"/>
    <jsp:forward page="index.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script type="text/javascript" >
            $(document).ready(function () {
                $("#estado").change(function () {
                    getCidades();
                });
            });
            function getCidades() {
                var estadoId = $("#estado").val();
                var url = "AJAXServlet";
                $.ajax({
                    url: url, // URL da sua Servlet
                    data: {
                        estadoId: estadoId
                    }, // Parâmetro passado para a Servlet
                    dataType: 'json',
                    success: function (data) {
                        // Se sucesso, limpa e preenche a combo de cidade
                        // alert(JSON.stringify(data));
                        $("#cidade").empty();
                        $.each(data, function (i, obj) {
                            $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
                        });
                    },
                    error: function (request, textStatus, errorThrown) {
                        alert(request.status + ', Error: ' + request.statusText);
                        // Erro
                    }
                });
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand mx-4" href="#">Portal</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Clientes">Clientes</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a class="btn btn-danger mx-5" type="submit" href="Logout">Logout</a>
                </form>
            </div>
        </nav>
        <main class="card shadow-lg mt-5 p-5 container">
            <h1 class="text-center">${form == 'alterar' ? 'Alterar' : 'Cadastrar' } Cliente</h1>
            <form method="post" class="mx-5" action="Clientes?action=${form == 'alterar' ? 'update' : 'new' }">
                <input type="hidden" value="${form == 'alterar' ? cliente.id : -1}" name="id">
                <div class="row my-2">
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" name="nome" value="${cliente.nome}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputEmail">Email</label>
                        <input type="email" class="form-control" id="inputEmail" name="email"  value="${cliente.email}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputCPF">CPF</label>
                        <input type="text" class="form-control" id="inputCPF" name="cpf"  value="${cliente.cpf}">
                    </div>
                    <div class="form-group col">
                        <label for="inputData">Data</label>
                        <input type="date" class="form-control" id="inputData" name="data"  value="${cliente.data}">
                    </div> 
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputRua">Rua</label>
                        <input type="text" class="form-control" id="inputRua" name="rua"  value="${cliente.rua}">
                    </div>
                    <div class="form-group col-1">
                        <label for="inputNum">N°</label>
                        <input type="text" class="form-control" id="inputNum" name="numero"  value="${cliente.numero}">
                    </div>
                    <div class="form-group col">
                        <label for="inputCEP">CEP</label>
                        <input type="text" class="form-control" id="inputCEP" name="cep" value="${cliente.cep}">
                    </div>
                </div>
                <div class="row my-2">
                    <div class="form-group col">
                        <label for="inputCidade">Cidade</label>
                        <select name="cidade" id="inputCidade" class="form-select">
                            <option selected value="${form == 'alterar' ? cliente.cidade.id : ''}">
                                ${form == 'alterar' ? cliente.cidade.nome : 'Selecione o estado...'}
                            </option>
                        </select>
                    </div>
                    <div class="form-group col">
                        <label for="inputUF">Estado</label>
                        <select name="inputUF" id="estado" class="form-select">
                            <c:if test="${form != 'alterar'}">
                                <option selected>
                                    Selecione...
                                </option>
                            </c:if>
                            <c:forEach var="estado" items="${requestScope.estados}">
                                <option ${estado.id==cliente.cidade.estado.id ? 'selected' :''} value="${estado.id}">
                                    ${estado.uf}</option>
                                </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row my-2 text-center">
                    <hr class="my-3">
                    <div>
                        <button type="submit" class="btn btn-primary">${form == 'alterar' ? 'Alterar':'Salvar'}</button>
                        <a class="btn btn-secondary mx-3" href="Clientes">Cancelar</a>
                    </div>
                </div>
            </form>
        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        Em caso de problemas, contactar o Administrador: ${configuracao.email}
    </footer>
</html>

