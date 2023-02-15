package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String BUSCAR = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM tb_usuario WHERE login_usuario = ?";
    private static final String BUSCAR_TODOS = "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM tb_usuario";
    private static final String INSERIR = "INSERT INTO tb_usuario(login_usuario,senha_usuario,nome_usuario) VALUES (?, ?, ?)";

    private Connection connection = null;

    public UsuarioDAO(Connection con) throws SQLException {
        if (con == null) {
            throw new SQLException("Conexão nula ao criar UsuarioDAO.");
        }
        this.connection = con;
    }

    public Usuario selecionarUsuario(String login) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Usuario usuario = new Usuario();
            st.setString(1, login);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
            }

            return usuario;
        } catch (SQLException e) {
            throw new SQLException("Erro buscando usuário: " + BUSCAR + "/ " + e);
        }
    }

    public List<Usuario> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Usuario> usuarios = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome_usuario"));
                u.setLogin(rs.getString("login_usuario"));
                u.setSenha(rs.getString("senha_usuario"));
                usuarios.add(u);
            }

            return usuarios;
        } catch (SQLException e) {
            throw new SQLException("Erro buscando usuários: " + BUSCAR_TODOS + "/ " + e);
        }
    }

    public void inserir(Usuario usuario) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, usuario.getLogin());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo usuário: " + INSERIR + "/ " + usuario.toString(), e);
        }
    }
}
