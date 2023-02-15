package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.exceptions.LoginException;
import java.sql.SQLException;
import java.util.List;

public class LoginFacade {

    public static void cadastrar(Usuario usuario) throws LoginException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(conn.getConnection());
            dao.inserir(usuario);
        } catch (SQLException e) {
            throw new LoginException("Erro ao cadastrar o usuario: " + usuario.getNome() + "ID: " + usuario.getId(), e);
        }
    }

    public static Usuario buscar(String id) throws LoginException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(con.getConnection());
            return dao.selecionarUsuario(id);
        } catch (SQLException e) {
            throw new LoginException("Erro na busca pelo usuario com o seguinte ID: " + id, e);
        }
    }

    public static List<Usuario> buscarTodos() {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(con.getConnection());
            return dao.selecionarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
