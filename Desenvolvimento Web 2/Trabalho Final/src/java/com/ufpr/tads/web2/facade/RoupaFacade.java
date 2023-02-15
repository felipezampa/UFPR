package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Roupa;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.RoupaDAO;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.RoupaException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class RoupaFacade {

    public static Roupa buscar(int idRoupa) throws RoupaException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            RoupaDAO dao = new RoupaDAO(con.getConnection());
            return dao.buscar(idRoupa);
        } catch (SQLException | DAOException e) {
            throw new RoupaException("Erro na busca pelo roupa com o seguinte id: " + idRoupa, e);
        }
    }

    public static void inserir(String nome, Double preco, Time prazo) throws RoupaException {
        Roupa roupa = new Roupa(0, nome, preco, prazo);

        try ( ConnectionFactory conn = new ConnectionFactory()) {
            RoupaDAO dao = new RoupaDAO(conn.getConnection());
            dao.inserir(roupa);
        } catch (SQLException | DAOException e) {
            throw new RoupaException("Erro ao cadastrar a roupa: " + roupa.getNome(), e);
        }
    }

    public static List<Roupa> buscarTodos() throws RoupaException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            RoupaDAO dao = new RoupaDAO(con.getConnection());
            return dao.buscarTodos();
        } catch (SQLException | DAOException e) {
            throw new RoupaException("Erro na busca das roupas: ", e);
        }
    }

    public static void alterar(int id, String nome, Double preco, Time prazo) throws RoupaException {
        Roupa roupa = new Roupa(id, nome, preco, prazo);
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            RoupaDAO dao = new RoupaDAO(conn.getConnection());
            dao.alterar(roupa);
        } catch (SQLException | DAOException e) {
            throw new RoupaException("Erro ao alterar a roupa: " + roupa.getNome(), e);
        }
    }

    public static void excluir(int idRoupa) throws RoupaException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            RoupaDAO dao = new RoupaDAO(conn.getConnection());
            dao.excluir(idRoupa);
        } catch (SQLException | DAOException e) {
            throw new RoupaException("Erro ao excluir a roupa com o seguinte id: " + idRoupa, e);
        }
    }
}
