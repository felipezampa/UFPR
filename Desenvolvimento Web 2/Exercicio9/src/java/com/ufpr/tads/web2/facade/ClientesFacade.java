package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.ClientesException;
import java.sql.SQLException;
import java.util.List;

public class ClientesFacade {

    public static void cadastrar(Cliente cliente) throws ClientesException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ClienteDAO dao = new ClienteDAO(conn.getConnection());
            dao.inserirCliente(cliente);
        } catch (SQLException e) {
            throw new ClientesException("Erro ao remover o Cliente: " + cliente.getNome() + "ID: " + cliente.getId(), e);
        }
    }

    public static void atualizar(Cliente cliente) throws ClientesException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ClienteDAO dao = new ClienteDAO(conn.getConnection());
            dao.atualizarCliente(cliente);
        } catch (SQLException e) {
            throw new ClientesException("Erro ao alterar o Cliente: " + cliente.getNome() + "ID: " + cliente.getId(), e);
        }
    }

    public static Cliente buscar(int id) throws ClientesException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            ClienteDAO dao = new ClienteDAO(con.getConnection());
            return dao.selecionarCliente(id);
        } catch (SQLException e) {
            throw new ClientesException("Erro na busca pelo cliente com o seguinte ID: " + id, e);
        }
    }

    public static List<Cliente> buscarTodos() {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            ClienteDAO dao = new ClienteDAO(con.getConnection());
            return dao.selecionarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void remover(int id) throws ClientesException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            ClienteDAO dao = new ClienteDAO(con.getConnection());
            dao.removerCliente(id);
        } catch (SQLException e) {
            throw new ClientesException("Erro removendo o cliente com o seguinte ID: " + id, e);
        }
    }

}
