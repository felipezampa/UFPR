/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.enums.*;
import com.ufpr.tads.web2.facade.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

/**
 *
 * @author tuica
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

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

        if ("cancelar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.cancelarPedido(id);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao cancelar pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("adicionarItem".equals(action)) {
            try {
                int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
                int roupaId = Integer.parseInt(request.getParameter("roupaId"));
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                ItensPedidoFacade.adicionarItem(pedidoId, roupaId, quantidade);
                
                getServletContext().getRequestDispatcher("/ClienteServlet?action=novoPedido").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao adicionar roupa ao pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("removerItem".equals(action)) {
            try {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                ItensPedidoFacade.removerItem(itemId);

                getServletContext().getRequestDispatcher("/ClienteServlet?action=novoPedido").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao remover roupa do pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("solicitarOrcamento".equals(action)) {
            try {
                int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.solicitarOrcamento(pedidoId);
                getServletContext().getRequestDispatcher("/ClienteServlet?action=orcamentos").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao solicitar orçamento" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("recolher".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.alteraSituacao(id, SituacaoPedido.RECOLHIDO);
            } catch (Exception ex) {
                request.setAttribute("errorPath","/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao cancelar pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("pagar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.atualizaDataPagamento(id, new Date());
                PedidoFacade.alteraSituacao(id, SituacaoPedido.PAGO);
            } catch (Exception ex) {
                request.setAttribute("errorPath","/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao pagar o pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("confirmarLavagem".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.alteraSituacao(id, SituacaoPedido.AGUARDANDO_PAGAMENTO);
            } catch (Exception ex) {
                request.setAttribute("errorPath","/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao cancelar pedido" + ex.getMessage());
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("finalizar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("pedidoId"));
                PedidoFacade.alteraSituacao(id, SituacaoPedido.FINALIZADO);
            } catch (Exception ex) {
                request.setAttribute("errorPath","/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao finalizar pedido" + ex.getMessage());
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
