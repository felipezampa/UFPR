package ex2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String login = request.getParameter("login");
        String senha = request.getParameter("password");

        if (validaLogin(login, senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("logado", login);

            try ( PrintWriter out = response.getWriter()) {
                out.println("<html><head><title>Login</title>");
                out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
                out.println("</head><body class=\"container\">");
                out.println("<h1 class=\"mt-5\">Bem vindo! – usuário " + login + " logado com sucesso</h1>");
                out.println("<a href=\"Portal\" class=\"btn btn-success mt-3\">PortalServlet</a>");
                out.println("</body></html>");
            }
        } else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<html><head><title>Login</title>");
                out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
                out.println("</head><body class=\"container\">");
                out.println("<h1 class=\"mt-5\">Usuário " + login + " não encontrado</h1>");
                out.println("<a href=\"index.html\" class=\"btn btn-danger mt-3\">Voltar ao Login</a>");
                out.println("</body></html>");
            }
        }
    }

    private boolean validaLogin(String user, String password) {
        boolean aux = user.equals(password);
        return aux;
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
