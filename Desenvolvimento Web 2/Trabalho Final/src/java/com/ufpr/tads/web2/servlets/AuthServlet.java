package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.exceptions.*;
import com.ufpr.tads.web2.facade.*;
import com.ufpr.tads.web2.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet(name = "AuthServlet", urlPatterns = {"/AuthServlet"})
public class AuthServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // ATRIBUTOS
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rdErro = getServletContext().getRequestDispatcher("/Erro/erro.jsp");
        RequestDispatcher rdIndex = getServletContext().getRequestDispatcher("/index.jsp");        
        // REDIRECIONAMENTOS
        if ("login".equals(action)) {
            try {
                String login = request.getParameter("login");
                String senha = request.getParameter("password");
                Usuario usuario = UsuarioFacade.login(login, senha);
                if (usuario == null) {
                    request.setAttribute("msg", "Usuário/Senha inválidos.");
                    rdIndex.forward(request, response);
                    return;
                }
                LoginBean loginBean = new LoginBean(usuario.getId(), usuario.getNome(), usuario.isFuncionario());
                session.setAttribute("login", loginBean);
                String servlet = loginBean.isFuncionario() ? "/FuncionarioServlet" : "/ClienteServlet";
                response.sendRedirect(request.getContextPath() + servlet);
            } catch (UsuarioException ex) {
                request.setAttribute("errorPath", "/index.jsp");
                request.setAttribute("msg", "Erro durante o login" + ex.getMessage());
                rdErro.forward(request, response);
            }
        } else if ("autoCadastro".equals(action)) {
            getServletContext().getRequestDispatcher("/create_account.jsp").forward(request, response);
        } else if ("logout".equals(action)) {
            if (session != null) {
                session.invalidate();
            }
            request.setAttribute("msg", "Usuário desconectado com sucesso!");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if ("salvarConta".equals(action)) {
            try {
                UsuarioFacade.inserir(
                        StringUtils.removeNonNumeric(request.getParameter("cpf")),
                        request.getParameter("email"),
                        UUID.randomUUID().toString(),
                        request.getParameter("nome"),
                        StringUtils.removeNonNumeric(request.getParameter("cep")),
                        request.getParameter("numero_rua"),
                        StringUtils.removeNonNumeric(request.getParameter("telefone")),
                        DateUtils.parseDate(request.getParameter("dataNascimento")),
                        false
                );
            } catch (UsuarioException ex) {
                request.setAttribute("errorPath", "/index.jsp");
                request.setAttribute("msg", "Erro durante a criação de conta");
                rdErro.forward(request, response);
            }
        } else {
            request.setAttribute("errorPath", "/index.jsp");
            request.setAttribute("msg", "Página não encontrada");
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
