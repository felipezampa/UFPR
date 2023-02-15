package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.CidadeDAO;
import java.sql.SQLException;

public class CidadeFacade {
    
    public static Cidade buscar(int id) {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            CidadeDAO dao = new CidadeDAO(con.getConnection());
            return dao.selecionarCidade(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
