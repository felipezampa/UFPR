<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <link rel="stylesheet" href="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>">
    </head>
    <body>
        <main class="container mt-5">
            <h1 class="text-center">Ocorreu um erro!</h1>
            <h3 class="text-danger bg-danger bg-opacity-10">ERRO: ${msg}</h3>
            <hr>
            <p> 
                ${pageContext.exception.message}
                ${pageContext.out.flush()}
                ${pageContext.exception.printStackTrace(pageContext.response.writer)}
            </p>
            <c:url var="erroURL" value="${errorPath}" context="${pageContext.request.contextPath}"/>
            <a href="${erroURL}" class="btn btn-primary">Voltar</a>
        </main>
    </body>
</html>
