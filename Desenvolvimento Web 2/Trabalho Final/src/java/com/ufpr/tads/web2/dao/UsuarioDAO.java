package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String BUSCAR = "SELECT id_usuario, cpf_usuario, login_usuario, senha_usuario, nome_usuario, cep_usuario, numero_usuario, telefone_usuario, "
            + "data_nascimento_usuario, is_funcionario FROM usuario WHERE login_usuario = ?";
    private static final String BUSCAR_FUNCIONARIOS = "SELECT id_usuario, cpf_usuario, login_usuario, senha_usuario, nome_usuario,cep_usuario,numero_usuario, telefone_usuario, "
            + "data_nascimento_usuario, is_funcionario FROM usuario WHERE is_funcionario = TRUE";
    private static final String INSERIR = "INSERT INTO usuario (cpf_usuario, login_usuario, senha_usuario, nome_usuario,cep_usuario,numero_usuario, telefone_usuario, "
            + "data_nascimento_usuario, is_funcionario) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE usuario SET cpf_usuario =?, login_usuario=?, nome_usuario=?, cep_usuario=?, numero_usuario=?, telefone_usuario=?,\n" +
"data_nascimento_usuario=? where id_usuario = ?;  ";
    private static final String DELETAR = "DELETE FROM usuario WHERE id_usuario = ?";
    
    private Connection connection = null;

    public UsuarioDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar UsuarioDAO.");
        }
        this.connection = con;
    }

    public Usuario buscar(String login) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {

            Usuario usuario = new Usuario();
            st.setString(1, login);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setCep(rs.getString("cep_usuario"));
                usuario.setNumero(rs.getString("numero_usuario"));
                usuario.setTelefone(rs.getString("telefone_usuario"));
                usuario.setDataNascimento(rs.getDate("data_nascimento_usuario"));
                usuario.setFuncionario(rs.getBoolean("is_funcionario"));
            }

            return usuario;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar usuario " + login + " | " + e);
        }
    }

    public List<Usuario> buscarFuncionarios() throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_FUNCIONARIOS)) {
            List<Usuario> usuarios = new ArrayList<>();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setCep(rs.getString("cep_usuario"));
                usuario.setNumero(rs.getString("numero_usuario"));
                usuario.setTelefone(rs.getString("telefone_usuario"));
                usuario.setDataNascimento(rs.getDate("data_nascimento_usuario"));
                usuario.setFuncionario(rs.getBoolean("is_funcionario"));
                usuarios.add(usuario);
            }

            return usuarios;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos os usuários: ", e);
        }
    }

    public void inserir(Usuario usuario) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, usuario.getCpf());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getSenha());
            st.setString(4, usuario.getNome());
            st.setString(5, usuario.getCep());
            st.setString(6, usuario.getNumero());
            st.setString(7, usuario.getTelefone());
            st.setDate(8, new java.sql.Date(usuario.getDataNascimento().getTime()));
            st.setBoolean(9, usuario.isFuncionario());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo usuário: " + usuario.toString(), e);
        }
    }

    public void atualizar(Usuario usuario) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, usuario.getCpf());
            st.setString(2, usuario.getLogin());
            st.setString(3, usuario.getNome());
            st.setString(4, usuario.getCep());
            st.setString(5, usuario.getNumero());
            st.setString(6, usuario.getTelefone());
            st.setDate(7, new java.sql.Date(usuario.getDataNascimento().getTime()));
            st.setInt(8, usuario.getId());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualzar usuário: " + usuario.toString(), e);
        }
    }

    public void deletar(int id) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(DELETAR)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro removendo usuário: " + id, e);
        }
    }
}
