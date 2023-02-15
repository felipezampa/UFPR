/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package exercicio1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Felipe Zampa
 */
@WebServlet(name = "SeptServlet", urlPatterns = {"/CursosSept"})
public class SeptServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cursos SEPT</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<style>");
            out.println("h1{font-family: verdana, sans-serif;color:#0275d8;} p{font-family: arial,sans-serif}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class=\"text-center\">SEPT - Setor de Educação Profissional e Tecnológica</h1>");
            out.println("<p class=\"text-center\">Rua Dr. Alcides Vieira Arcoverde, 1225 - Jardim das Américas - Curitiba (PR) - Brasil</p><hr>");
            out.println("<div class=\"container mt-1\">");
            out.println("<table class=\"table table-striped table-hover\">");
            out.println("<thead>");
            out.println("<tr><th>Curso</th><th>Página</th></tr>");
            out.println("</thead>");
            out.println("<tbody>");
            ArrayList<CursosSept> cursos = CursosSept.getCursos();
            for (CursosSept curso : cursos) {
                out.println("<tr>");
                out.println("<td>" + curso.getNome() + "</td>");
                out.println("<td><a target=\"_blank\" href=\"" + curso.getLink() + "\">" + curso.getLink() + "</a></td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<div class=\"text-center\"><a class=\"btn btn-primary text-white mt-2 text-center\" href=\"meutads.jsp\">Meu TADS</a></div></div>");
            out.println("</body>");
            out.println("</body>");
            out.println("</html>");
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
