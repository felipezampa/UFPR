package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.UsuarioException;
import com.ufpr.tads.web2.utils.EncryptUtils;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UsuarioFacade {

    public static Usuario login(String login, String senha) throws UsuarioException {
        Usuario usuario = buscar(login);
        if (usuario == null) {
            return null;
        }
        String senhaCriptografada = EncryptUtils.encrypt(senha);
        if (senhaCriptografada == null || !senhaCriptografada.equals(usuario.getSenha())) {
            return null;
        }
        return usuario;
    }

    public static Usuario buscar(String login) throws UsuarioException {
        try ( ConnectionFactory con = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(con.getConnection());
            return dao.buscar(login);
        } catch (SQLException | DAOException e) {
            throw new UsuarioException("Erro na busca pelo usuario com o seguinte Login: " + login, e);
        }
    }

    public static void inserir(String cpf, String email, String senha, String nome, String cep, String numero, String telefone, Date dataNascimento, Boolean isFuncionario) throws UsuarioException {
        Usuario usuario = new Usuario(0, cpf, email, EncryptUtils.encrypt(senha), nome, cep, numero, telefone, dataNascimento, isFuncionario);

        if (!isFuncionario) {
            System.out.println("EMAIL: Senha gerada para o usuário: " + email + " = " + senha);
        }

        try ( ConnectionFactory conn = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(conn.getConnection());
            dao.inserir(usuario);
        } catch (SQLException | DAOException e) {
            throw new UsuarioException("Erro ao cadastrar o usuario: " + usuario.getNome() + "ID: " + usuario.getId(), e);
        }
    }
    
        public static void atualizar(int id,String cpf, String email, String senha, String nome, String cep, String numero, String telefone, Date dataNascimento, Boolean isFuncionario) throws UsuarioException {
        Usuario usuario = new Usuario(id, cpf, email, senha, nome, cep, numero, telefone, dataNascimento, isFuncionario);
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(conn.getConnection());
            dao.atualizar(usuario);
        } catch (SQLException | DAOException e) {
            throw new UsuarioException("Erro ao atualizar o usuario: " + usuario.getNome() + "ID: " + usuario.getId(), e);
        }
    }
    
    
    

    public static void deletar(int id) throws UsuarioException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(conn.getConnection());
            dao.deletar(id);
        } catch (SQLException | DAOException e) {
            throw new UsuarioException("Erro ao deletar o usuario com o seguinte ID: " + id, e);
        }
    }

    public static List<Usuario> buscarFuncionarios() throws UsuarioException {;
        try ( ConnectionFactory con = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(con.getConnection());
            return dao.buscarFuncionarios();
        } catch (DAOException | SQLException e) {
            throw new UsuarioException("Erro na busca dos funcionários: ", e);
        }
    }

}
