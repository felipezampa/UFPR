package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Produto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {

    public static final String INSERIR = "INSERT INTO tb_atendimento(dt_hr_atendimento, nome_tipo_atendimento, dsc_atendimento, id_produto, id_tipo_atendimento, id_usuario, id_cliente, res_atendimento)VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String BUSCAR_TODOS = "SELECT id_atendimento, dt_hr_atendimento, nome_tipo_atendimento, dsc_atendimento, id_produto, id_tipo_atendimento, id_usuario, id_cliente, res_atendimento FROM tb_atendimento";
    public static final String BUSCAR = "SELECT id_atendimento, dt_hr_atendimento, nome_tipo_atendimento, dsc_atendimento, id_produto, id_tipo_atendimento, id_usuario, id_cliente, res_atendimento FROM tb_atendimento WHERE id_atendimento = ? LIMIT 1";
    public static final String EXCLUIR = "DELETE FROM tb_atendimento WHERE id_atendimento= ?";
    public static final String ALTERAR = "UPDATE tb_atendimento SET dt_hr_atendimento=?, nome_tipo_atendimento=?, dsc_atendimento=?, id_produto=?, id_tipo_atendimento=?, id_usuario=?, id_cliente=?, res_atendimento=? WHERE id_atendimento= ?";

    private Connection connection = null;

    public AtendimentoDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula criar a AtendimentoDAO");
        }
        this.connection = connection;
    }

    public void inserirAtendimento(Atendimento atendimento) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setDate(1, new java.sql.Date(atendimento.getData().getTime()));
            st.setString(2, atendimento.getNome());
            st.setString(3, atendimento.getDescricao());
            st.setInt(4, atendimento.getProduto().getId());
            st.setInt(5, atendimento.getTipoAtendimento().getId());
            st.setInt(6, atendimento.getUsuario().getId());
            st.setInt(7, atendimento.getCliente().getId());
            st.setString(8, atendimento.getResposta());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo cliente: " + INSERIR + e);
        }
    }

    public List<Atendimento> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Atendimento> allAtendimentos = new ArrayList();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId(rs.getInt("id_atendimento"));
                atendimento.setData(rs.getDate("dt_hr_atendimento"));
                atendimento.setNome(rs.getString("nome_tipo_atendimento"));
                atendimento.setDescricao(rs.getString("dsc_atendimento"));
                atendimento.setProduto(new ProdutoDAO(this.connection).selecionarProduto(rs.getInt("id_produto")));    
                atendimento.setTipoAtendimento(new TipoAtendimentoDAO(this.connection).selecionarTipoAtendimento(rs.getInt("id_tipo_atendimento")));
                atendimento.setUsuario(new UsuarioDAO(this.connection).selecionarUsuario(rs.getString("id_usuario")));
                atendimento.setCliente(new ClienteDAO(this.connection).selecionarCliente(rs.getInt("id_cliente")));                
                atendimento.setResposta(rs.getString("res_atendimento"));

                allAtendimentos.add(atendimento);
            }

            return allAtendimentos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos os atendimentos" + BUSCAR_TODOS + e);
        }
    }

    public Atendimento selecionarAtendimento(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Atendimento atendimento = new Atendimento();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                atendimento.setId(rs.getInt("id_atendimento"));
                atendimento.setData(rs.getDate("dt_hr_atendimento"));
                atendimento.setNome(rs.getString("nome_tipo_atendimento"));
                atendimento.setDescricao(rs.getString("dsc_atendimento"));
                atendimento.setProduto(new ProdutoDAO(this.connection).selecionarProduto(rs.getInt("id_produto")));    
                atendimento.setTipoAtendimento(new TipoAtendimentoDAO(this.connection).selecionarTipoAtendimento(rs.getInt("id_tipo_atendimento")));
                atendimento.setUsuario(new UsuarioDAO(this.connection).selecionarUsuario(rs.getString("id_usuario")));
                atendimento.setCliente(new ClienteDAO(this.connection).selecionarCliente(rs.getInt("id_cliente")));                
                atendimento.setResposta(rs.getString("res_atendimento"));
            }

            return atendimento;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar atendimento por ID: " + BUSCAR + e);
        }
    }

    public void atualizarAtendimento(Atendimento atendimento) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {
            st.setInt(9, atendimento.getId());
            st.setDate(1, new java.sql.Date(atendimento.getData().getTime()));
            st.setString(2, atendimento.getNome());
            st.setString(3, atendimento.getDescricao());
            st.setInt(4, atendimento.getProduto().getId());
            st.setInt(5, atendimento.getTipoAtendimento().getId());
            st.setInt(6, atendimento.getUsuario().getId());
            st.setInt(7, atendimento.getCliente().getId());
            st.setString(8, atendimento.getResposta());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando atendimento: " + ALTERAR + e);
        }
    }

    public void removerAtendimento(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando atendimento: " + EXCLUIR + e);
        }
    }

}
