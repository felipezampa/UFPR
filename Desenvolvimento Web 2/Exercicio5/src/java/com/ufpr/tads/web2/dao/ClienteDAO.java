package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String BUSCAR = "SELECT id_cliente, cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente,uf_cliente FROM tb_cliente WHERE id_cliente = ?";
    private static final String BUSCAR_TODOS = "SELECT id_cliente, cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente,uf_cliente FROM tb_cliente";
    private static final String INSERIR = "INSERT INTO tb_cliente (id_cliente,cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente,uf_cliente) VALUES (DEFAULT,?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ALTERAR = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, cidade_cliente = ?, uf_cliente = ?  WHERE id_cliente = ?";
    private static final String EXCLUIR = "DELETE FROM tb_cliente WHERE id_cliente = ?";

    private Connection connection = null;

    public ClienteDAO(Connection con) throws SQLException {
        if (con == null) {
            throw new SQLException("Conexão nula ao criar ClienteDAO.");
        }
        this.connection = con;
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
            st.setString(8, cliente.getCidade());
            st.setString(9, cliente.getUf());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar inserir cliente: " + INSERIR + e);
        }
    }

    public List<Cliente> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Cliente> clientes = new ArrayList<>();

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
                cliente.setCidade(rs.getString("cidade_cliente"));
                cliente.setUf(rs.getString("uf_cliente"));

                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos os clientes: " + BUSCAR_TODOS + e);
        }
    }

    public Cliente selecionarCliente(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Cliente cliente = new Cliente();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setData(rs.getDate("data_cliente"));
                cliente.setRua(rs.getString("rua_cliente"));
                cliente.setNumero(rs.getInt("nr_cliente"));
                cliente.setCep(rs.getString("cep_cliente"));
                cliente.setCidade(rs.getString("cidade_cliente"));
                cliente.setUf(rs.getString("uf_cliente"));
            }

            return cliente;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar o cliente pelo ID: " + BUSCAR + e);
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
            st.setString(8, cliente.getCidade());
            st.setString(9, cliente.getUf());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o cliente: " + ALTERAR + e);
        }
    }

    public void removerCliente(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir o cliente: " + EXCLUIR + e);
        }
    }
}
