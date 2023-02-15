package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.help.ConverterData;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AlterarClienteServlet", urlPatterns = {"/AlterarCliente"})
public class AlterarClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");

            rd.forward(request, response);
        }
        try ( ConnectionFactory db = new ConnectionFactory()) {
            int id = Integer.parseInt(request.getParameter("id"));
            int numero = Integer.parseInt(request.getParameter("numero"));
            Date data = ConverterData.converteData(request.getParameter("data"));

            Cliente cliente = new Cliente();

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

            ClienteDAO dao = new ClienteDAO(db.getConnection());

            dao.atualizarCliente(cliente);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes");
            rd.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
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
