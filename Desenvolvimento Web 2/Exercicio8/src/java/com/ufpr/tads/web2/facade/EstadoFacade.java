package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.EstadoDAO;
import java.sql.SQLException;
import java.util.List;

public class EstadoFacade {

    public static List<Estado> buscarTodos() {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            EstadoDAO dao = new EstadoDAO(con.getConnection());
            return dao.selecionarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
