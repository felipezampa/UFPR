package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(urlPatterns = {"/GeradorRelatorio"})
public class GeradorRelatorio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        // Verifica se o usuário está logado
        if (session.getAttribute("login") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");

            rd.forward(request, response);
        }

        String action = request.getParameter("action");

        if ("clientes".equals(action) || action == null) {
            try ( ConnectionFactory factory = new ConnectionFactory()) {
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                // Caminho contextualizado do relatório compilado
                String jasper = request.getContextPath() + "/allClientes.jasper";
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(),
                        params,
                        factory.getConnection());
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
        if ("datas".equals(action) || action == null) {
            try ( ConnectionFactory factory = new ConnectionFactory()) {
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                // Caminho contextualizado do relatório compilado
                String jasper = request.getContextPath() + "/intervaloDatas.jasper";
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(),
                        params,
                        factory.getConnection());
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
        if ("resolvidos".equals(action) || action == null) {
            try ( ConnectionFactory factory = new ConnectionFactory()) {
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                // Caminho contextualizado do relatório compilado
                String jasper = request.getContextPath() + "/resolvidos.jasper";
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(),
                        params,
                        factory.getConnection());
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
        if ("tipo".equals(action) || action == null) {
            try ( ConnectionFactory factory = new ConnectionFactory()) {
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                // Caminho contextualizado do relatório compilado
                String jasper = request.getContextPath() + "/tipoAtendimento.jasper";
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(),
                        params,
                        factory.getConnection());
                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (JRException e) {
                request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GeradorRelatorio.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GeradorRelatorio.class.getName()).log(Level.SEVERE, null, ex);
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
