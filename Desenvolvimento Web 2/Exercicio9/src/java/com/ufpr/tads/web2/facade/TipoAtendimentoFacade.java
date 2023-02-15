package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;
import com.ufpr.tads.web2.exceptions.TipoAtendimentoException;
import java.sql.SQLException;

public class TipoAtendimentoFacade {
            public static TipoAtendimento buscar(int id) throws TipoAtendimentoException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            TipoAtendimentoDAO dao = new TipoAtendimentoDAO(con.getConnection());
            return dao.selecionarTipoAtendimento(id);
        } catch (SQLException e) {
            throw new TipoAtendimentoException("Erro na busca pelo tipo de atendimento com o seguinte ID: " + id, e);
        }
    }
}
