<%-- 
    Document   : portal.jsp
    Created on : 17 de nov. de 2022, 23:12:45
    Author     : felip
--%>
<jsp:useBean id="login" class="com.ufpr.tads.web2.beans.LoginBean" scope="session">
    <%
        RequestDispatcher rs = getServletContext().getRequestDispatcher("/erro.jsp");
        
        request.setAttribute("msg", "ERRO - Usuário não está logado");
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
        <title>Portal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container">
        <h1>Olá <jsp:getProperty name="login" property="nomeUsuario" />, Seja bem vindo!</h1>
        <hr>
        <a href="inserir.jsp" class="btn btn-primary">Adicionar</a>
        <a href="Logout" class="btn btn-secondary">Logout</a>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        <jsp:useBean  id="configuracao"  class="com.ufpr.tads.web2.beans.ConfigBean"  scope="application"  />
        Em caso de problemas, contactar o Administrador: 
        <jsp:getProperty name="configuracao" property="email" />
    </footer>
</html>
