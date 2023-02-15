<%-- 
    Document   : inserir
    Created on : 17 de nov. de 2022, 23:16:53
    Author     : felip
--%>
<jsp:useBean id="login" class="com.ufpr.tads.web2.beans.LoginBean" scope="session">
    <%
        RequestDispatcher rs = getServletContext().getRequestDispatcher("/erro.jsp");
        
        request.setAttribute("msg", "Usuário não está logado");
        request.setAttribute("page", "index.html");
        
        session.invalidate();
        rs.forward(request, response);
    %>
</jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-5">
        <h1 class="text-center">Cadastro</h1>
        <form action="Cadastrar" method="post">
            <label for="nome">Nome</label>
            <input type="text" id="nome" class="form-control" name="nome"></input>

            <label for="login">Login</label>
            <input type="text" id="login" class="form-control" name="login"></input>

            <label for="password">Senha</label>
            <input type="text" id="password" class="form-control" name="password"></input>
            
            <input type="submit" class="btn btn-primary mt-3" value="Salvar"/>
        </form>
    </body>
</html>
