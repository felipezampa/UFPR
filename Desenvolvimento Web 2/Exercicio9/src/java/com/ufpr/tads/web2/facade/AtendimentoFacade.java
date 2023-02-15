package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.AtendimentoException;
import java.sql.SQLException;
import java.util.List;

public class AtendimentoFacade {

    public static void cadastrar(Atendimento atendimento) throws AtendimentoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            AtendimentoDAO dao = new AtendimentoDAO(conn.getConnection());
            dao.inserirAtendimento(atendimento);
        } catch (SQLException e) {
            throw new AtendimentoException("Erro ao cadastrar o atendimento: " + atendimento.getNome() + "ID: " + atendimento.getId(), e);
        }
    }

    public static void atualizar(Atendimento atendimento) throws AtendimentoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            AtendimentoDAO dao = new AtendimentoDAO(conn.getConnection());
            dao.atualizarAtendimento(atendimento);
        } catch (SQLException e) {
            throw new AtendimentoException("Erro ao alterar o Atendimento: " + atendimento.getNome() + "ID: " + atendimento.getId(), e);
        }
    }

    public static Atendimento buscar(int id) throws AtendimentoException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            AtendimentoDAO dao = new AtendimentoDAO(con.getConnection());
            return dao.selecionarAtendimento(id);
        } catch (SQLException e) {
            throw new AtendimentoException("Erro na busca pelo atendimento com o seguinte ID: " + id, e);
        }
    }

    public static List<Atendimento> buscarTodos() {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            AtendimentoDAO dao = new AtendimentoDAO(con.getConnection());
            return dao.selecionarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void remover(int id) throws AtendimentoException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            AtendimentoDAO dao = new AtendimentoDAO(con.getConnection());
            dao.removerAtendimento(id);
        } catch (SQLException e) {
            throw new AtendimentoException("Erro removendo o atendimento com o seguinte ID: " + id, e);
        }
    }
}
