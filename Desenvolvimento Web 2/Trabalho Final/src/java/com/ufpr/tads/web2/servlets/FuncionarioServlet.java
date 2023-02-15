package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.exceptions.PedidoException;
import com.ufpr.tads.web2.exceptions.UsuarioException;
import com.ufpr.tads.web2.facade.PedidoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;
import com.ufpr.tads.web2.utils.DateUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        LoginBean loginBean = (LoginBean) session.getAttribute("login");
        if (loginBean == null) {
            session.invalidate();
            request.setAttribute("msg", "Usuário deve se autenticar para usar o sistema");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else if (!loginBean.isFuncionario()) {
            request.setAttribute("errorPath", "/ClienteServlet");
            request.setAttribute("msg", "Essa página é restrita para funcionários");
            getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "portalFuncionario";
        }
        request.setAttribute("sidebarAction", action);

        if ("portalFuncionario".equals(action)) {
            try {
                List<Pedido> listPedido = PedidoFacade.getPedidoBuscaOrderDate();
                request.setAttribute("pedidos", listPedido);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/portalFuncionario.jsp");
                rd.forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao buscar funcionário");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("pedidos".equals(action)) {

            try {
                String filtrohoje = request.getParameter("filtro");
                List<Pedido> listPedido;
                if ("dias".equals(filtrohoje)) {
                    if (request.getParameter("dataInicio").equals("") || request.getParameter("dataFim").equals("")) {
                        listPedido = PedidoFacade.getPedidoAll();
                    } else {
                        listPedido = PedidoFacade.getBuscaPedidoDataInFin(
                                DateUtils.parseDate(request.getParameter("dataInicio")),
                                DateUtils.parseDate(request.getParameter("dataFim")));
                    }

                } else if ("hoje".equals(filtrohoje)) {
                    listPedido = PedidoFacade.getBuscaPedidoHoje();
                } else {
                    listPedido = PedidoFacade.getPedidoAll();
                }

                request.setAttribute("pedidos", listPedido);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/pedidosFuncionario.jsp");
                rd.forward(request, response);
            } catch (PedidoException ex) {
                request.setAttribute("errorPath", "/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao buscar funcionário");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("manterFuncionarios".equals(action)) {
            try {
                List<Usuario> listFuncionario = UsuarioFacade.buscarFuncionarios();
                request.setAttribute("funcionario", listFuncionario);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/manterFuncionarios.jsp");
                rd.forward(request, response);
            } catch (UsuarioException ex) {
                request.setAttribute("errorPath", "/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao buscar funcionário");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else if ("salvarFuncionario".equals(action)) {

            String id = request.getParameter("atualizar");
            if (!"".equals(id)) {
                try {
                    int idInt = Integer.parseInt(id);
                    UsuarioFacade.atualizar(
                            idInt,
                            request.getParameter("cpf"),
                            request.getParameter("email"),
                            UUID.randomUUID().toString(),
                            request.getParameter("nome"),
                            request.getParameter("cep"),
                            request.getParameter("numero"),
                            request.getParameter("telefone"),
                            DateUtils.parseDate(request.getParameter("dataNascimento")),
                            true);
                    List<Usuario> listFuncionario = UsuarioFacade.buscarFuncionarios();
                    request.setAttribute("funcionario", listFuncionario);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/manterFuncionarios.jsp");
                    rd.forward(request, response);
                } catch (UsuarioException ex) {
                    request.setAttribute("errorPath", "/FuncionarioServlet");
                    request.setAttribute("msg", "Erro ao salvar funcionário");
                    getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
                }
            } else {
                try {
                    UsuarioFacade.inserir(
                            request.getParameter("cpf"),
                            request.getParameter("email"),
                            UUID.randomUUID().toString(),
                            request.getParameter("nome"),
                            request.getParameter("cep"),
                            request.getParameter("numero"),
                            request.getParameter("telefone"),
                            DateUtils.parseDate(request.getParameter("dataNascimento")),
                            true
                    );
                    List<Usuario> listFuncionario = UsuarioFacade.buscarFuncionarios();
                    request.setAttribute("funcionario", listFuncionario);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/manterFuncionarios.jsp");
                    rd.forward(request, response);
                } catch (UsuarioException ex) {
                    request.setAttribute("errorPath", "/FuncionarioServlet");
                    request.setAttribute("msg", "Erro ao salvar funcionário");
                    getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
                }
            }
        } else if ("deletar".equals(action)) {
            try {
                UsuarioFacade.deletar(Integer.parseInt(request.getParameter("id")));
                List<Usuario> listFuncionario = UsuarioFacade.buscarFuncionarios();
                request.setAttribute("funcionario", listFuncionario);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/manterFuncionarios.jsp");
                rd.forward(request, response);
            } catch (UsuarioException ex) {
                request.setAttribute("errorPath", "/FuncionarioServlet");
                request.setAttribute("msg", "Erro ao deletar funcionário");
                getServletContext().getRequestDispatcher("/Erro/erro.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorPath", "/FuncionarioServlet");
            request.setAttribute("msg", "Página não encontrada");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro/erro.jsp");
            rd.forward(request, response);
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
