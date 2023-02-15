package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    
    public static final String INSERIR  = "INSERT INTO tb_estado (nome_estado,sigla_estado) VALUES (?, ?)";
    public static final String BUSCAR_TODOS = "SELECT id_estado,nome_estado,sigla_estado FROM tb_estado";
    public static final String BUSCAR = "SELECT id_estado,nome_estado,sigla_estado FROM tb_estado WHERE id_estado = ? LIMIT 1";
    public static final String EXCLUIR = "DELETE FROM tb_estado WHERE id_estado = ?";
    public static final String ALTERAR = "UPDATE tb_estado SET nome_estado = ?, sigla_estado = ? WHERE id_estado = ?";

    private Connection connection = null;

    public EstadoDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula ao tentar criar o EstadoDAO");
        }

        this.connection = connection;
    }

    public void inserirEstado(Estado estado) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, estado.getNome());
            st.setString(2, estado.getUf());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo estado: " + INSERIR + e);
        }
    }

    public List<Estado> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Estado> allEstados = new ArrayList();

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Estado estado = new Estado();

                estado.setId(rs.getInt("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setUf(rs.getString("sigla_estado"));

                allEstados.add(estado);
            }

            return allEstados;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos estados: " + BUSCAR_TODOS + e);
        }
    }

    public Estado selecionarEstado(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Estado estado = new Estado();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                estado.setId(rs.getInt("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setUf(rs.getString("sigla_estado"));
            }

            return estado;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar estado por ID: " + BUSCAR + e);
        }
    }

    public void removerEstado(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando estado: " + EXCLUIR + e);
        }
    }

    public void atualizarEstado(Estado estado) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {
            
            st.setString(1, estado.getNome());
            st.setString(2, estado.getUf());
            st.setInt(3, estado.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando estado: " + ALTERAR + e);
        }
    }  
}
