package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.exceptions.ClientesException;
import com.ufpr.tads.web2.facade.ClientesFacade;
import com.ufpr.tads.web2.help.ConverterData;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //Checa se está logado ao sistema
        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");

            rd.forward(request, response);
            return;
        }

        // Obtem o action selecionado para a Controller
        String action = request.getParameter("action");

        if ("list".equals(action) || action == null) {
            List<Cliente> clientes = ClientesFacade.buscarTodos();

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
            request.setAttribute("clientes", clientes);

            rd.forward(request, response);
            return;
        }

        if ("show".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID para checar se ele existe
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }

            try {
                int id = Integer.parseInt(strId);
                Cliente cliente = ClientesFacade.buscar(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
                request.setAttribute("cliente", cliente);

                rd.forward(request, response);
                return;
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if ("formUpdate".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID para checar se ele existe
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }

            try {
                int id = Integer.parseInt(strId);
                Cliente cliente = ClientesFacade.buscar(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesAlterar.jsp");
                request.setAttribute("cliente", cliente);

                rd.forward(request, response);
                return;
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if ("remove".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID para checar se ele existe
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }

            try {
                int id = Integer.parseInt(strId);
                ClientesFacade.remover(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes");
                rd.forward(request, response);
                return;
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if ("update".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
                return;
            }

            try {
                Cliente cliente = new Cliente();
                int id = Integer.parseInt(strId);
                int numero = Integer.parseInt(request.getParameter("numero"));
                Date data = ConverterData.converteData(request.getParameter("data"));

                // Insere os dados parseados e coletados no objeto Cliente
                cliente.setId(id);
                cliente.setNumero(numero);
                cliente.setData(data);
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setCep(request.getParameter("cep"));
                cliente.setCidade(request.getParameter("cidade"));
                cliente.setUf(request.getParameter("uf"));

                // Atualiza o cliente
                ClientesFacade.alterar(cliente);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes");
                rd.forward(request, response);
                return;
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if ("formNew".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesNovo.jsp");
            rd.forward(request, response);
            return;
        }

        if ("new".equals(action)) {
            try {
                Cliente cliente = new Cliente();

                // Tenta parsear os dados
                int numero = Integer.parseInt(request.getParameter("numero"));
                Date data = ConverterData.converteData(request.getParameter("data"));

                // Insere os dados parseados e coletados na instância de Cliente
                cliente.setNumero(numero);
                cliente.setData(data);
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setCep(request.getParameter("cep"));
                cliente.setCidade(request.getParameter("cidade"));
                cliente.setUf(request.getParameter("uf"));

                // Atualiza o cliente
                ClientesFacade.inserir(cliente);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes");
                rd.forward(request, response);
                return;
            } catch (ClientesException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
