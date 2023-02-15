package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Roupa;
import com.ufpr.tads.web2.exceptions.RoupaException;
import com.ufpr.tads.web2.facade.RoupaFacade;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.Time;

@WebServlet(name = "RoupaServlet", urlPatterns = {"/RoupaServlet"})
public class RoupaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // AJUSTA ENCODING PRA NAO VIR PALAVRA COM CARACTERE ESTRANHO
        request.setCharacterEncoding("UTF-8");
        // ATRIBUTOS
        HttpSession session = request.getSession();
        LoginBean loginBean = (LoginBean) session.getAttribute("login");
        RequestDispatcher rdErro = getServletContext().getRequestDispatcher("/Erro/erro.jsp");
        RequestDispatcher rdIndex = getServletContext().getRequestDispatcher("/RoupaServlet?action=listarRoupas");
        String action = request.getParameter("action");
        // VALIDACAO
        if (loginBean == null) {
            session.invalidate();
            request.setAttribute("msg", "Usuário deve se autenticar para usar o sistema");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else if (!loginBean.isFuncionario()) {
            request.setAttribute("errorPath", "/ClienteServlet");
            request.setAttribute("msg", "Essa página é restrita para funcionários");
            rdErro.forward(request, response);
        }
        // REDIRECIONAMENTOS
        if ("listarRoupas".equals(action) || action == null) {
            try {
                List<Roupa> roupas = RoupaFacade.buscarTodos();
                request.setAttribute("roupas", roupas);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/listarRoupas.jsp");
                rd.forward(request, response);
            } catch (RoupaException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Não foi possível listar as roupas");
                rdErro.forward(request, response);
            }
        } else if ("formNew".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/formRoupas.jsp");
            rd.forward(request, response);
        } else if ("formUpdate".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Roupa roupa = RoupaFacade.buscar(id);
                request.setAttribute("roupa", roupa);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/formRoupas.jsp");
                rd.forward(request, response);
            } catch (RoupaException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Erro ao abrir formulário de edição");
                rdErro.forward(request, response);
            } catch (NumberFormatException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Erro ao converter o id para número");
                rdErro.forward(request, response);
            }
        } else if ("inserir".equals(action)) {
            try {
                String nome = request.getParameter("nome");
                Double preco = Double.parseDouble(request.getParameter("preco"));
                Time prazo = Time.valueOf(request.getParameter("prazo"));
                RoupaFacade.inserir(nome, preco, prazo);
                response.sendRedirect("RoupaServlet");
            } catch (RoupaException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Não foi possível cadastrar a roupa");
                rdErro.forward(request, response);
            }
        } else if ("atualizar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                Double preco = Double.parseDouble(request.getParameter("preco"));
                Time prazo = Time.valueOf(request.getParameter("prazo"));
                RoupaFacade.alterar(id, nome, preco, prazo);
                rdIndex.forward(request, response);
            } catch (RoupaException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Não foi possível cadastrar a roupa");
                rdErro.forward(request, response);
            }
        } else if ("deletar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                RoupaFacade.excluir(id);
                rdIndex.forward(request, response);
            } catch (RoupaException ex) {
                request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
                request.setAttribute("msg", "Não foi possível deletar a roupa");
                rdErro.forward(request, response);
            }
        } else {
            request.setAttribute("errorPath", "/RoupaServlet?action=listarRoupas");
            request.setAttribute("msg", "Action nao encontrada");
            rdErro.forward(request, response);
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
