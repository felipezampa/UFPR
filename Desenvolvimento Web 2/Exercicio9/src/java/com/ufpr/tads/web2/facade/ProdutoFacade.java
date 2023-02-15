package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import com.ufpr.tads.web2.exceptions.ProdutoException;
import java.sql.SQLException;

public class ProdutoFacade {
        public static Produto buscar(int id) throws ProdutoException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            ProdutoDAO dao = new ProdutoDAO(con.getConnection());
            return dao.selecionarProduto(id);
        } catch (SQLException e) {
            throw new ProdutoException("Erro na busca pelo produto  com o seguinte ID: " + id, e);
        }
    }
}
