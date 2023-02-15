<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Cadastro</title>
        <link rel="stylesheet" href="assets/css/create_account.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
              integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>
    <body>
        <main>
            <div class="container">
                <div class="login-redirect">
                    <h1>Já é cadastrado?</h1>
                    <p>Clique no botão abaixo para fazer o login.</p>
                    <a href="${pageContext.request.contextPath}"><button>Login</button></a>
                </div>
                <div class="login-create">
                    <div class="line-create">
                        <div class="linhas">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="bolas">
                            <nav>
                                <div></div>
                            </nav>
                            <nav>
                                <div></div>
                            </nav>
                            <nav>
                                <div></div>
                            </nav>
                        </div>
                    </div>
                    <h1 id="alterText">Criar conta</h1>
                    <c:url var="criarContaURL" value="/AuthServlet" context="${pageContext.request.contextPath}" >
                        <c:param name="action" value="salvarConta" />
                    </c:url>
                    <div id="form-class">
                        <form action="${criarContaURL}" method="post" class="email_senha" id="email_senha">
                            <div class="primeiraDiv" id="primeiraDiv">
                                <input type="text" name="nome" id="name_create" placeholder="Nome completo" maxlength="100">
                                <input type="text" name="cpf" id="cpf_create" placeholder="CPF" maxlength="11">
                                <input type="text" name="email" id="email_create" placeholder="Email" maxlength="50">
                                <input type="text" name="telefone" placeholder="Telefone" maxlength="11">
                                <input placeholder="Data de Nascimento" type="text" name="dataNascimento" id="dataNascimento_create" onfocus="(this.type='date')" onblur="if(this.value==''){this.type='text'}"">
                            </div>
                            <div class="endereco" id="endereco">
                                <input class="create_cep" type="text" name="cep" placeholder="C.E.P" maxlength="8">
                                <input class="create_estado" type="text" name="estado" placeholder="Estado">
                                <input type="text" name="cidade" placeholder="Cidade">
                                <input class="create_rua" type="text" name="rua" placeholder="Rua">
                                <input class="create_number" type="text" name="numero_rua" placeholder="N°" maxlength="20">

                            </div>
                            <div class="signin_confirm" id="signin_confirm">
                                <input id="" type="submit" value="Finalizar">
                            </div>
                        </form>

                        <div class="signin_button" id="signin_button">
                            <button class="buttonPreview" id="buttonPreview"> <i class="fas fa-arrow-left"></i></button>
                            <button class="buttonNext" id="buttonNext"> <i class="fas fa-arrow-right"></i></button>
                        </div>

                        <div class="create_sucess" id="create_sucess">
                            <h1>Cadastrado realizado com sucesso, verifique seu email</h1>
                            <i class="fa fa-check-circle" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
        </main>
        <script src="assets/js/CreateAccount.js"></script>
        <script src="assets/js/FormSubmit.js"></script>
        <script src="assets/js/ValidateForm.js"></script>
    </body>
</html>