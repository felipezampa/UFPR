package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static final String INSERIR = "INSERT INTO tb_cliente (cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,id_cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String BUSCAR_TODOS = "SELECT id_cliente,cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,id_cidade FROM tb_cliente";
    public static final String BUSCAR = "SELECT id_cliente,cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,id_cidade FROM tb_cliente WHERE id_cliente = ? LIMIT 1";
    public static final String EXCLUIR = "DELETE FROM tb_cliente WHERE id_cliente = ?";
    public static final String ALTERAR = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, id_cidade = ?  WHERE id_cliente = ?";

    private Connection connection = null;

    public ClienteDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula ao tentar criar o ClienteDAO");
        }

        this.connection = connection;
    }

    public void inserirCliente(Cliente cliente) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setDate(4, new java.sql.Date(cliente.getData().getTime()));
            st.setString(5, cliente.getRua());
            st.setInt(6, cliente.getNumero());
            st.setString(7, cliente.getCep());
            st.setInt(8, cliente.getCidade().getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo cliente: " + INSERIR + e);
        }
    }

    public List<Cliente> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Cliente> allClientes = new ArrayList();

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setData(rs.getDate("data_cliente"));
                cliente.setRua(rs.getString("rua_cliente"));
                cliente.setNumero(rs.getInt("nr_cliente"));
                cliente.setCep(rs.getString("cep_cliente"));
                cliente.setCidade(new CidadeDAO(this.connection).selecionarCidade(rs.getInt("id_cidade")));

                allClientes.add(cliente);
            }

            return allClientes;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos os clientes" + BUSCAR_TODOS + e);
        }
    }

    public Cliente selecionarCliente(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Cliente cliente = new Cliente();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setData(rs.getDate("data_cliente"));
                cliente.setRua(rs.getString("rua_cliente"));
                cliente.setNumero(rs.getInt("nr_cliente"));
                cliente.setCep(rs.getString("cep_cliente"));
                cliente.setCidade(new CidadeDAO(this.connection).selecionarCidade(rs.getInt("id_cidade")));
            }

            return cliente;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar cliente por ID: " + BUSCAR + e);
        }
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {

            st.setInt(10, cliente.getId());

            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setDate(4, new java.sql.Date(cliente.getData().getTime()));
            st.setString(5, cliente.getRua());
            st.setInt(6, cliente.getNumero());
            st.setString(7, cliente.getCep());
            st.setInt(8, cliente.getCidade().getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando cliente: " + ALTERAR + e);
        }
    }

    public void removerCliente(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando cliente: " + EXCLUIR + e);
        }
    }

}
