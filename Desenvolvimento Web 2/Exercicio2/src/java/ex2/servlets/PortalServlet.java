package ex2.servlets;

import ex2.objects.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PortalServlet", urlPatterns = {"/Portal"})
public class PortalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            List<Usuario> users = new ArrayList<>();

            users.add(new Usuario("Felipe", "felipe", "123"));
            users.add(new Usuario("Jimi Hendrix", "hendrix", "purplehaze"));
            users.add(new Usuario("John Frusciante", "frusciante", "rhcp50"));
            users.add(new Usuario("Java", "java", "java"));
            users.add(new Usuario("Angus Young", "angus", "acdc"));
            users.add(new Usuario("Zakk Wylde", "zakk", "wahwah2020"));
            users.add(new Usuario("Ozzy Osbourne", "ozzy", "hellraiser"));
            users.add(new Usuario("PostgreSQL", "postgres", "postgres"));

            /* TODO output your page here. You may use following sample code. */
            out.println("<html><head><title>Portal</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head><body class=\"container\"><div class=\"row\">");
            out.println("<div class=\"col mx-3\">");
            out.println("<h1 class=\"mt-5 text-center\">Cadastrar Usuário</h1>");
            out.println("<form action=\"Cadastrar\" method=\"post\">");
            out.println("<label for=\"nome\">Nome: </label>");
            out.println("<input type=\"text\" id=\"nome\" class=\"form-control\" name=\"nome\">");
            out.println("<label for=\"login\">Login: </label>");
            out.println("<input type=\"text\" id=\"login\" class=\"form-control\" name=\"login\">");
            out.println("<label for=\"senha\">Senha: </label>");
            out.println("<input type=\"password\" id=\"senha\" class=\"form-control\" name=\"senha\">");
            out.println("<input type=\"submit\" class=\"btn btn-primary mt-3\" value=\"Salvar\" />");
            out.println("</form>");
            out.println("</div>");
            out.println("<div class=\"col mx-3\"><h1 class=\"mt-5 text-center\">Lista de Usuários</h1>");
            out.println("<table class=\"table table-striped table-hover\">");
            out.println("<thead class=\"table-dark\">");
            out.println("<tr><th>Nome</th><th>Login</th><th>Senha</th>");
            out.println("<tbody>");
            for (Usuario usuario : users) {
                out.println("<tr>");
                out.println("<td>" + usuario.getNome() + "</td>");
                out.println("<td>" + usuario.getLogin() + "</td>");
                out.println("<td>" + usuario.getSenha() + "</td>");
                out.println("</tr>");
            };
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div></div>");
            out.println("<div class=\"text-center mt-5\"><a href=\"Logout\" class=\"btn btn-lg btn-danger mt-5\">Logout</a>");
            out.println("</div></body></html>");
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
