package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.enums.SituacaoPedido;
import com.ufpr.tads.web2.exceptions.PedidoException;
import com.ufpr.tads.web2.facade.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            action = "portalCliente";
        }
        request.setAttribute("sidebarAction", action);

        if ("portalCliente".equals(action)) {
            try {
                List<Pedido> pedidosEmAberto = PedidoFacade.getPedidosPorUsuarioESituacao(loginBean.getIdUsuario(), SituacaoPedido.EM_ABERTO);
                request.setAttribute("pedidos", pedidosEmAberto);
                getServletContext().getRequestDispatcher("/Cliente/portalCliente.jsp").forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao listar pedidos em aberto");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("novoPedido".equals(action)) {
            try {
                List<Roupa> roupas = RoupaFacade.buscarTodos();
                Pedido pedido = PedidoFacade.buscarAguardandoOrcamentoOuCriarNovo(loginBean.getIdUsuario());
                List<ItensPedido> itens = ItensPedidoFacade.buscarPorPedido(pedido.getId());

                request.setAttribute("roupas", roupas);
                request.setAttribute("pedido", pedido);
                request.setAttribute("itensPedido", itens);
                getServletContext().getRequestDispatcher("/Cliente/novoPedido.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao listar roupas");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("listaPedidos".equals(action)) {
            try {
                List<Pedido> pedidos;

                String filtroSituacao = request.getParameter("situacaoFiltro");
                if (filtroSituacao == null || "TODAS".equals(filtroSituacao)) {
                    pedidos = PedidoFacade.buscarTodosPorCliente(loginBean.getIdUsuario());
                } else {
                    pedidos = PedidoFacade.getPedidosPorUsuarioESituacao(loginBean.getIdUsuario(), SituacaoPedido.valueOf(filtroSituacao));
                }

                request.setAttribute("pedidos", pedidos);
                request.setAttribute("filtroSituacao", filtroSituacao);
                request.setAttribute("situacoesParaFiltrar", SituacaoPedido.values());
                getServletContext().getRequestDispatcher("/Cliente/listaPedidos.jsp").forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao listar pedidos");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("consultar".equals(action)) {
            try {
                List<Pedido> pedidos;

                String filtroNum = request.getParameter("filtrarNum");
              
                if (filtroNum == null || "".equals(filtroNum)) {
                    pedidos = PedidoFacade.buscarTodosPorCliente(loginBean.getIdUsuario());
                } else {
                   Integer idPedido = Integer.valueOf(filtroNum);
                   pedidos = PedidoFacade.buscarClienteNumPedido(loginBean.getIdUsuario(), idPedido);
                }

                request.setAttribute("pedidos", pedidos);
                getServletContext().getRequestDispatcher("/Cliente/consultarPedido.jsp").forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/ClienteServlet");
                request.setAttribute("msg", "Erro ao listar pedidos");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("orcamentos".equals(action)) {
            try {
                List<Pedido> orcamentos = PedidoFacade.getPedidosPorUsuarioESituacao(loginBean.getIdUsuario(), SituacaoPedido.AGUARDANDO_APROVACAO);
                request.setAttribute("orcamentos", orcamentos);
                getServletContext().getRequestDispatcher("/Cliente/orcamentoCliente.jsp").forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/OrcamentoServlet");
                request.setAttribute("msg", "Erro ao listar orcamentos em aberto");
            }
        } else if ("historico".equals(action)) {
            getServletContext().getRequestDispatcher("/historicoCliente.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "Página não encontrada");
            getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
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
