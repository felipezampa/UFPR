package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoDAO {

    public static final String INSERIR = "INSERT INTO tb_tipo_atendimento(nome_tipo_atendimento) VALUES(?)";
    public static final String BUSCAR_TODOS = "SELECT id_tipo_atendimento, nome_tipo_atendimento FROM tb_tipo_atendimento";
    public static final String BUSCAR = "SELECT id_tipo_atendimento, nome_tipo_atendimento FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ? LIMIT 1";
    public static final String EXCLUIR = "DELETE FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ?";
    public static final String ALTERAR = "UPDATE tb_tipo_atendimento SET nome_tipo_atendimento= ? WHERE id_tipo_atendimento=?";

    private Connection connection = null;

    public TipoAtendimentoDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula ao tentar criar o TipoAtendimentoDAO");
        }

        this.connection = connection;
    }

    public void inserirTipoAtendimento(TipoAtendimento atendimento) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, atendimento.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo atendimento: " + INSERIR + e);
        }
    }

    public List<TipoAtendimento> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<TipoAtendimento> allProdutos = new ArrayList();

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TipoAtendimento tipo = new TipoAtendimento();
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
                allProdutos.add(tipo);
            }

            return allProdutos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos os tipo de atendimentos" + BUSCAR_TODOS + e);
        }
    }

    public TipoAtendimento selecionarTipoAtendimento(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            TipoAtendimento tipo = new TipoAtendimento();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                tipo.setId(rs.getInt("id_tipo_atendimento"));
                tipo.setNome(rs.getString("nome_tipo_atendimento"));
            }

            return tipo;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar o tipo de atendimento por ID: " + BUSCAR + e);
        }
    }

    public void atualizarTipoAtendimento(TipoAtendimento tipo) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {

            st.setInt(2, tipo.getId());
            
            st.setString(1, tipo.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando produto: " + ALTERAR + e);
        }
    }

    public void removerTipoAtendimento(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando tipo de atendimento: " + EXCLUIR + e);
        }
    }

}