/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.facade.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

/**
 *
 * @author tuica
 */
@WebServlet(name = "OrcamentoServlet", urlPatterns = {"/OrcamentoServlet"})
public class OrcamentoServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        LoginBean loginBean = (LoginBean) session.getAttribute("login");
        if (loginBean == null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (loginBean.isFuncionario()) {
            request.setAttribute("errorPath", "/FuncionarioServlet");
            request.setAttribute("msg", "Essa página é restrita para clientes");
            getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
        }

        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("errorPath", loginBean.isFuncionario() ? "/FuncionarioServlet" : "/ClienteServlet");
            request.setAttribute("msg", "É obrigatório informar uma ação");
            getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
        }

        if ("aprovar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                OrcamentoFacade.aprovarOrcamento(id);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/OrcamentoServlet");
                request.setAttribute("msg", "Erro ao aprovar o orçamento" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("rejeitar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                OrcamentoFacade.rejeitarOrcamento(id);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/OrcamentoServlet");
                request.setAttribute("msg", "Erro ao rejeitar o orçamento" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Página não encontrada");
            getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
        }
    }

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
