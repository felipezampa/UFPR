package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.SQLException;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.beans.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String login = request.getParameter("login");;
        String senha = request.getParameter("password");

        try ( ConnectionFactory conn = new ConnectionFactory()) {

            UsuarioDAO dao = new UsuarioDAO(conn.getConnection());
            Usuario usuario = null;
            if (login != null && senha != null) {
                usuario = dao.selecionarUsuario(login);
            }

            if (login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                HttpSession session = request.getSession();
                LoginBean loginBean = new LoginBean(usuario.getId(), usuario.getNome());

                session.setAttribute("login", loginBean);
                response.sendRedirect("portal.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");

                request.setAttribute("msg", "Usuário/Senha inválidos.");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            System.out.println("Erro na conexão" + e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
