package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Produto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static final String INSERIR = "INSERT INTO tb_produto(nome_produto) VALUES(?)";
    public static final String BUSCAR_TODOS = "SELECT id_produto, nome_produto FROM tb_produto";
    public static final String BUSCAR = "SELECT id_produto, nome_produto FROM tb_produto WHERE id_produto = ? LIMIT 1";
    public static final String EXCLUIR = "DELETE FROM tb_produto WHERE id_produto = ?";
    public static final String ALTERAR = "UPDATE tb_produto SET nome_produto= ? WHERE id_produto=?";

    private Connection connection = null;

    public ProdutoDAO(Connection connection) throws SQLException {
        if (connection == null) {
            throw new SQLException("Conexão nula ao tentar criar o ProdutoDAO");
        }

        this.connection = connection;
    }

    public void inserirProduto(Produto produto) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, produto.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro inserindo produto: " + INSERIR + e);
        }
    }

    public List<Produto> selecionarTodos() throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Produto> allProdutos = new ArrayList();

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome_produto"));
                allProdutos.add(produto);
            }

            return allProdutos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar todos os produtos" + BUSCAR_TODOS + e);
        }
    }

    public Produto selecionarProduto(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Produto produto = new Produto();

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                produto.setId(rs.getInt("id_cliente"));
                produto.setNome(rs.getString("nome_cliente"));
            }

            return produto;
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar buscar produto por ID: " + BUSCAR + e);
        }
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {

            st.setInt(2, produto.getId());
            
            st.setString(1, produto.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro atualizando produto: " + ALTERAR + e);
        }
    }

    public void removerProduto(int id) throws SQLException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro deletando produto: " + EXCLUIR + e);
        }
    }

}
