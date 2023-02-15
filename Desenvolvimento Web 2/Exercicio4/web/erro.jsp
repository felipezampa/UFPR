<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <main class="container mt-5">
            <h1 class="text-center">Ocorreu um errro</h1>
            <hr>
            <p>Erro: <%= request.getAttribute("msg") %></p>
            <a href="<%= request.getAttribute("page") %>" class="btn btn-primary">Voltar ao Login</a>
        </main>
        <footer class="fixed-bottom bg-secondary text-white text-center">
            <jsp:useBean  id="configuracao"  class="com.ufpr.tads.web2.beans.ConfigBean"  scope="application"  />
                Em caso de problemas, contactar o Administrador: 
            <jsp:getProperty name="configuracao" property="email" />
        </footer>
    </body>
</html>
