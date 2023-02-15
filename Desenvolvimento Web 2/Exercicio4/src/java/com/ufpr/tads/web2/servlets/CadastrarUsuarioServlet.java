package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.SQLException;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.beans.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/Cadastrar"})
public class CadastrarUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");

            request.setAttribute("msg", "Usuário não está logado");
            request.setAttribute("page", "index.html");

            rd.forward(request, response);
        }

        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("password");
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setNome(nome);
        usuario.setSenha(senha);

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html><head><title>Cadastro</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<meta charset=\"UTF-8\">");
            out.println("</head><body class=\"container\">");

            try ( ConnectionFactory factory = new ConnectionFactory();) {
                UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
                dao.inserir(usuario);
                out.println("<h1 class=\"mt-5\">Usuário " + nome + " cadastrado com sucesso</h1>");
            } catch (SQLException e) {
                System.out.println("Erro:" + e);
                out.println("<h1 class=\"mt-5\">Ocorreu um erro ao tentar cadastrar o usuário" + nome + "</h1>");
            }

            out.println("<a href=\"portal.jsp\" class=\"btn btn-primary mt-3\">Portal</a>");
            out.println("</body></html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadastrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
