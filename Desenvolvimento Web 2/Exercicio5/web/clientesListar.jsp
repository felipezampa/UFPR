<%-- 
    Document   : clientesListar
    Created on : 11 de dez. de 2022, 22:58:04
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.ufpr.tads.web2.beans.Cliente" %>

<jsp:useBean id="login" class="com.ufpr.tads.web2.beans.LoginBean" scope="session">
    <%
        RequestDispatcher rs = getServletContext().getRequestDispatcher("/index.jsp");
        
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
        
        rs.forward(request, response);
    %>
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand mx-4" href="#">Portal</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Clientes">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="FormNovoCliente">Novo Cliente</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a class="btn btn-danger mx-5" type="submit" href="Logout">Logout</a>
                </form>
            </div>
        </nav>
        <main class="card shadow-lg mt-5 p-5 container text-center">
            <div><a href="FormNovoCliente" class="btn btn-primary mb-2">Novo Cliente</a></div>
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                     List<Cliente> clientes = (List<Cliente>)request.getAttribute("clientes");
                     for(Cliente cliente: clientes){
                    %>
                    <tr>
                        <td><%= cliente.getNome() %></td>
                        <td><%= cliente.getCpf() %></td>
                        <td><%= cliente.getEmail() %></td>
                        <td class="col-3">
                            <a href="VisualizarCliente?id=<%= cliente.getId() %>" class="btn btn-success">Visualizar</a>
                            <a href="FormAlterarCliente?id=<%= cliente.getId() %>" class="btn btn-primary">Alterar</a>
                            <a href="RemoverCliente?id=<%= cliente.getId() %>" class="btn btn-danger">Remover</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

        </main>
    </body>
    <footer class="fixed-bottom bg-secondary text-white text-center">
        <jsp:useBean  id="configuracao"  class="com.ufpr.tads.web2.beans.ConfigBean"  scope="application"  />
        Em caso de problemas, contactar o Administrador: 
        <jsp:getProperty name="configuracao" property="email" />
    </footer>
</html>
