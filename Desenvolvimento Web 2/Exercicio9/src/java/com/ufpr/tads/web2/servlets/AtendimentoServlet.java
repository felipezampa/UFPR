/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.exceptions.AtendimentoException;
import com.ufpr.tads.web2.exceptions.ClientesException;
import com.ufpr.tads.web2.exceptions.LoginException;
import com.ufpr.tads.web2.exceptions.ProdutoException;
import com.ufpr.tads.web2.exceptions.TipoAtendimentoException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClientesFacade;
import com.ufpr.tads.web2.facade.LoginFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.TipoAtendimentoFacade;
import com.ufpr.tads.web2.services.ConverterData;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felip
 */
@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/Atendimento"})
public class AtendimentoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Verifica se o usuário está logado
        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");

            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // Se está logado, verifica qual action do Controller ele irá chamar
        String action = request.getParameter("action");

        if ("list".equals(action) || action == null) {
            List<Atendimento> atendimentos = AtendimentoFacade.buscarTodos();

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
            request.setAttribute("atendimentos", atendimentos);

            rd.forward(request, response);
        }

        if ("show".equals(action)) {
            String strId = request.getParameter("id");

            // Valida o recebimento do ID no request
            if (strId == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Invocação inválida: ID é nulo");
                rd.forward(request, response);
            }

            try {
                int id = Integer.parseInt(strId);
                Atendimento atendimento = AtendimentoFacade.buscar(id);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoDetalhes.jsp");
                request.setAttribute("atendimento", atendimento);

                rd.forward(request, response);
            } catch (AtendimentoException | NumberFormatException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }

        if ("formNew".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimento.jsp");
            rd.forward(request, response);
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
        processRequest(request, response);
// Se está logado, verifica qual action do Controller ele irá chamar
        String action = request.getParameter("action");

        if ("new".equals(action)) {
            try {
                Atendimento atendimento = new Atendimento();

                // Tenta parsear os dados
                int produto = Integer.parseInt(request.getParameter("produto"));
                int tipoAtendimento = Integer.parseInt(request.getParameter("tipoAtendimento"));
                int cliente = Integer.parseInt(request.getParameter("cliente"));
                Date data = ConverterData.converteData(request.getParameter("data"));

                // Insere os dados parseados e coletados na instância de Cliente
                atendimento.setData(data);
                atendimento.setNome("nome");
                atendimento.setDescricao("descricao");
                atendimento.setProduto(ProdutoFacade.buscar(produto));
                atendimento.setTipoAtendimento(TipoAtendimentoFacade.buscar(tipoAtendimento));
                atendimento.setUsuario(LoginFacade.buscar("usuario"));
                atendimento.setCliente(ClientesFacade.buscar(cliente));
                atendimento.setResposta("resposta");

                // Atualiza o cliente
                AtendimentoFacade.cadastrar(atendimento);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Atendimento/");
                rd.forward(request, response);
            } catch (AtendimentoException | NumberFormatException | ClientesException 
                    | LoginException | ProdutoException | TipoAtendimentoException e) {
                request.setAttribute("jakarta.servlet.jsp.jspException", e);
                request.setAttribute("jakarta.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
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
