package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.utils.DateUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Date;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ATRIBUTOS
        HttpSession session = request.getSession();
        LoginBean loginBean = (LoginBean) session.getAttribute("login");
        String action = request.getParameter("action");
        RequestDispatcher rdErro = getServletContext().getRequestDispatcher("/Erro/erro.jsp");
        String host = "http://" + request.getServerName() + ":" + request.getServerPort();
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
        try ( ConnectionFactory factory = new ConnectionFactory()) {
            if ("indexRelatorios".equals(action) || action == null) {
                request.setAttribute("sidebarAction", action);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Funcionario/relatorios.jsp");
                rd.forward(request, response);
            } else if ("clientes".equals(action)) {
                String jasper = request.getContextPath() + "/Relatorios/clientes.jasper";
                URL jasperURL = new URL(host + jasper);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if ("clientesFieis".equals(action)) {
                String jasper = request.getContextPath() + "/Relatorios/clientesFieis.jasper";
                URL jasperURL = new URL(host + jasper);
                HashMap params = new HashMap();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if ("receitas".equals(action)) {
                Date dataInicio = DateUtils.parseDate(request.getParameter("dataInicio"));
                Date dataFim = DateUtils.parseDate(request.getParameter("dataFim"));
                String jasper = request.getContextPath() + "/Relatorios/receitas.jasper";
                URL jasperURL = new URL(host + jasper);
                HashMap params = new HashMap();
                params.put("DATA_INICIO", dataInicio);
                params.put("DATA_FIM", dataFim);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else if ("pedidos".equals(action)) {
                Date dataInicio = DateUtils.parseDate(request.getParameter("dataInicio"));
                Date dataFim = DateUtils.parseDate(request.getParameter("dataFim"));
                String jasper = request.getContextPath() + "/Relatorios/pedidos.jasper";
                URL jasperURL = new URL(host + jasper);
                HashMap params = new HashMap();
                params.put("DATA_INICIO", dataInicio);
                params.put("DATA_FIM", dataFim);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, factory.getConnection());
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } else {
                request.setAttribute("msg", "Erro, action nao conhecida");
                rdErro.forward(request, response);
            }
        } catch (JRException e) {
            request.setAttribute("msg", "Erro no Jasper : " + e.getMessage());
            rdErro.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("msg", "Erro no SQL : " + e.getMessage());
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
