<%-- 
    Document   : meutads
    Created on : 28 de out. de 2022, 18:34:46
    Author     : Felipe Zampa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="exercicio1.IntegrantesEquipe" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meu TADS</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <nav class="navbar bg-dark">
                <img src="http://www.sept.ufpr.br/portal/analisesistemas/wp-content/themes/wpufpr_zurb6_sept/images/logo_ufpr_branca.png" alt="Logo Tads" height="55px" class="mx-5"/>
                <h1 class="text-white text-center">ANALISE E DESENVOLVIMENTO DE SISTEMAS</h1>
                <ul class="navbar-nav">
                    <li>
                        <a class="btn btn-primary text-white mx-5" href="CursosSept">Voltar</a>
                    </li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="container mt-5">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Rede Social</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            ArrayList<IntegrantesEquipe> pessoas = IntegrantesEquipe.getIntegrantes();
                            for (IntegrantesEquipe integrantes: pessoas){
                                out.println("<tr>");
                                out.println("<td>" + integrantes.getNome() + "</td>");
                                out.println("<td><a target=\"_blank\" href=\"" + integrantes.getSocial() +"\">" + integrantes.getSocial() + "</a></td>");
                                out.println("</tr>");
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </main>
        <footer class="fixed-bottom mb-5">
            <a target="_blank" class="btn btn-outline-secondary mx-5" href="https://www.youtube.com/watch?v=3x6ONgHHe4s">Click me XD</a>
        </footer>
    </body>
</html>
