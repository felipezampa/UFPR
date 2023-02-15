package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Estado;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
    
    public static final String INSERIR = "INSERT INTO tb_cidade (nome_cidade,id_estado) VALUES (?, ?)";
    public static final String BUSCAR_TODOS = "SELECT id_cidade,nome_cidade,id_estado FROM tb_cidade";
    public static final String BUSCAR = "SELECT id_cidade,nome_cidade,id_estado FROM tb_cidade WHERE id_cidade = ? LIMIT 1";
    public static final String BUSCAR_POR_ESTADO  = "SELECT id_cidade,nome_cidade,id_estado FROM tb_cidade WHERE id_estado = ?";
    public static final String EXCLUIR = "DELETE FROM tb_cidade WHERE id_cidade = ?";
    public static final String ALTERAR = "UPDATE tb_cidade SET nome_cidade = ?, id_estado = ? WHERE id_cidade = ?";

    private Connection connection = null;

    public CidadeDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula criar a CidadeDAO");
        }

        this.connection = connection;
    }

    public void inserirCidade(Cidade cidade) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, cidade.getNome());
            st.setInt(2, cidade.getEstado().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo cidade: " + INSERIR + e);
        }
    }

    public List<Cidade> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Cidade> allCidades = new ArrayList();

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();

                cidade.setId(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                
                int idEstado = rs.getInt("id_cidade");
                cidade.setEstado(new EstadoDAO(this.connection).selecionarEstado(idEstado));

                allCidades.add(cidade);
            }

            return allCidades;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todas as cidades" + BUSCAR_TODOS + e);
        }
    }

    public Cidade selecionarCidade(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Cidade cidade = new Cidade();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cidade.setId(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                
                int idEstado = rs.getInt("id_estado");
                
                cidade.setEstado(new EstadoDAO(this.connection).selecionarEstado(idEstado));
            }
            
            System.out.println(cidade.getEstado().getNome());
            return cidade;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar cidade por ID: " + BUSCAR + e);
        }
    }
    
    public List<Cidade> buscarPorEstado(int idEstado) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_ESTADO)) {
            List<Cidade> allCidades = new ArrayList(); 
            
            st.setInt(1, idEstado);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();

                cidade.setId(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));

                allCidades.add(cidade);
            }
            
            
            Estado estado = new EstadoDAO(this.connection).selecionarEstado(idEstado);
            for(Cidade cidade : allCidades) {
                cidade.setEstado(estado);
            }

            return allCidades;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todas as cidades" + BUSCAR_POR_ESTADO + e);
        }
    }

    public void removerCidade(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando cidade: " + EXCLUIR + e);
        }
    }

    public void atualizarCidade(Cidade cidade) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {
            
            st.setString(1, cidade.getNome());
            st.setInt(2, cidade.getEstado().getId());
            st.setInt(3, cidade.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando cidade: " + ALTERAR + e);
        }
    }
}
