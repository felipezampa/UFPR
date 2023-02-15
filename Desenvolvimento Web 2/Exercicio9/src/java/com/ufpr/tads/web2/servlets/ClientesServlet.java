package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.exceptions.ClientesException;
import com.ufpr.tads.web2.facade.CidadeFacade;
import com.ufpr.tads.web2.facade.ClientesFacade;
import com.ufpr.tads.web2.facade.EstadoFacade;
import com.ufpr.tads.web2.services.ConverterData;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ClientesServlet", urlPatterns = {"/Clientes"})
public class ClientesServlet extends HttpServlet {

    protected void verificarUsuarioLogado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Verifica se o usuário está logado
        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");

            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        verificarUsuarioLogado(request, response);
        
        // Se está logado, verifica qual action do Controller ele irá chamar
        String action = request.getParameter("action");

        if ("list".equals(action) || action == null) {
            List<Cliente> clientes = ClientesFacade.buscarTodos();

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
            request.setAttribute("clientes", clientes);

            rd.forward(request, response);
        }

        if ("show".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
            }

            try {
                int id = Integer.parseInt(strId);
                Cliente cliente = ClientesFacade.buscar(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
                request.setAttribute("cliente", cliente);

                rd.forward(request, response);
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }

        if ("formUpdate".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
            }

            try {
                int id = Integer.parseInt(strId);
                Cliente cliente = ClientesFacade.buscar(id);
                List<Estado> estados = EstadoFacade.buscarTodos();

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
                request.setAttribute("cliente", cliente);
                request.setAttribute("estados", estados);
                request.setAttribute("form", "alterar");

                rd.forward(request, response);
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }

        if ("remove".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
            }

            try {
                int id = Integer.parseInt(strId);
                ClientesFacade.remover(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes");
                rd.forward(request, response);
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }

        if ("formNew".equals(action)) {
            List<Estado> estados = EstadoFacade.buscarTodos();
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
            request.setAttribute("estados", estados);
            request.setAttribute("form", "novo");
            
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        verificarUsuarioLogado(request, response);
        
        // Se está logado, verifica qual action do Controller ele irá chamar
        String action = request.getParameter("action");
        
        if ("new".equals(action)) {
            try {
                Cliente cliente = new Cliente();

                // Tenta parsear os dados
                int numero = Integer.parseInt(request.getParameter("numero"));
                int idCidade = Integer.parseInt(request.getParameter("cidade"));
                Date data = ConverterData.converteData(request.getParameter("data"));

                // Insere os dados parseados e coletados na instância de Cliente
                cliente.setNumero(numero);
                cliente.setData(data);
                cliente.setCidade(CidadeFacade.buscar(idCidade));
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setCep(request.getParameter("cep"));

                // Atualiza o cliente
                ClientesFacade.cadastrar(cliente);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/");
                rd.forward(request, response);
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }
        
        if ("update".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
            }

            try {
                Cliente cliente = new Cliente();

                // Tenta parsear os dados
                int id = Integer.parseInt(strId);
                int numero = Integer.parseInt(request.getParameter("numero"));
                int idCidade = Integer.parseInt(request.getParameter("cidade"));
                Date data = ConverterData.converteData(request.getParameter("data"));

                // Insere os dados parseados e coletados na instância de Cliente
                cliente.setId(id);
                cliente.setNumero(numero);
                cliente.setData(data);
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setCep(request.getParameter("cep"));
                cliente.setCidade(CidadeFacade.buscar(idCidade));

                // Atualiza o cliente
                ClientesFacade.atualizar(cliente);
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/");
                rd.forward(request, response);
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
