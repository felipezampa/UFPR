<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Erro/erro.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOL - Lavanderia-OnLine</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="<c:url value='/assets/img/favicon.ico'/>">
        <link rel="stylesheet" href="assets/css/login.css"/>
    </head>
    <body>
        <main>
            <div class="container">
                <div class="sign_in">
                    <c:if test="${!empty msg}">
                        <div class="text-danger bg-danger bg-opacity-10 px-2">
                            <h2 class="text-danger">${msg}</h2>
                        </div>
                        <hr>
                    </c:if>
                    <h1>LOL - Lavanderia On-Line</h1>
                    <div>
                        <c:url var="loginURL" value="/AuthServlet" context="${pageContext.request.contextPath}" >
                            <c:param name="action" value="login" />
                        </c:url>
                        <form action="${loginURL}" method="post" class="email_senha">
                            <input type="text" name="login" placeholder="Email">
                            <input type="password" name="password" placeholder="Senha">
                            <input type="submit" value="Entrar" class="signin_button">
                        </form>
                    </div>
                </div>
                <div class="sign_up">
                    <h1>Criar uma conta</h1>
                    <p>Clique no botão abaixo caso ainda não tenha conta.</p>
                    <c:url var="cadastroURL" value="/AuthServlet" context="${pageContext.request.contextPath}" >
                        <c:param name="action" value="autoCadastro" />
                    </c:url>
                    <button>
                        <a href="${cadastroURL}">Cadastrar-se</a>
                    </button>
                </div>
            </div>
        </main>
    </body>
</html>
