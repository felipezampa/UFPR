package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.exceptions.*;

import java.sql.*;
import java.util.*;

public class ItensPedidoDAO {
    
    private static final String BUSCAR = "SELECT ip.id_itens_pedido, ip.id_pedido, ip.quantidade, ip.id_roupa, r.nome_roupa, r.preco_roupa, r.prazo_roupa FROM itens_pedido ip join roupa r on ip.id_roupa = r.id_roupa where ip.id_itens_pedido = ?;";
    private static final String BUSCAR_POR_PEDIDO = "SELECT ip.id_itens_pedido, ip.id_pedido, ip.quantidade, ip.id_roupa, r.nome_roupa, r.preco_roupa, r.prazo_roupa FROM itens_pedido ip join roupa r on ip.id_roupa = r.id_roupa where ip.id_pedido = ?;";
    private static final String INSERIR = "INSERT INTO itens_pedido (id_pedido, id_roupa, quantidade) VALUES (?, ?, ?)";
    private static final String SOMA_VALOR_ITENS_PEDIDO = "SELECT SUM(ip.quantidade * r.preco_roupa) as valor FROM itens_pedido ip join roupa r on ip.id_roupa = r.id_roupa where ip.id_pedido = ?;";
    private static final String DELETAR = "DELETE FROM itens_pedido WHERE id_itens_pedido = ?;";

    private Connection connection = null;

    public ItensPedidoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar UsuarioDAO.");
        }
        this.connection = con;
    }

    public ItensPedido buscar(int id) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ItensPedido itensPedido = new ItensPedido();
                itensPedido.setId(rs.getInt("id_itens_pedido"));
                itensPedido.setPedidoId(rs.getInt("id_pedido"));
                itensPedido.setQuantidade(rs.getInt("quantidade"));

                Roupa roupa = new Roupa();
                roupa.setId(rs.getInt("id_roupa"));
                roupa.setNome(rs.getString("nome_roupa"));
                roupa.setPreco(rs.getDouble("preco_roupa"));
                roupa.setPrazo(rs.getTime("prazo_roupa"));
                itensPedido.setRoupa(roupa);

                return itensPedido;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar itensPedido " + id + " | " + e);
        }
    }
    public List<ItensPedido> buscarPorPedido(int pedidoId) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_PEDIDO)) {
            List<ItensPedido> itensPedidos = new ArrayList<>();
            st.setInt(1, pedidoId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ItensPedido itensPedido = new ItensPedido();
                itensPedido.setId(rs.getInt("id_itens_pedido"));
                itensPedido.setPedidoId(rs.getInt("id_pedido"));
                itensPedido.setQuantidade(rs.getInt("quantidade"));

                Roupa roupa = new Roupa();
                roupa.setId(rs.getInt("id_roupa"));
                roupa.setNome(rs.getString("nome_roupa"));
                roupa.setPreco(rs.getDouble("preco_roupa"));
                roupa.setPrazo(rs.getTime("prazo_roupa"));
                itensPedido.setRoupa(roupa);

                itensPedidos.add(itensPedido);
            }
            return itensPedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar itensPedido " + pedidoId + " | " + e);
        }
    }
    
    public void inserir(ItensPedido itensPedido) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setInt(1, itensPedido.getPedidoId());
            st.setInt(2, itensPedido.getRoupa().getId());
            st.setInt(3, itensPedido.getQuantidade());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao inserir itensPedido " + itensPedido + " | " + e);
        }
    }

    public void deletar(ItensPedido item) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(DELETAR)) {
            st.setInt(1, item.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao deletar itensPedido " + item.getPedidoId() + " | " + e);
        }
    }

    public Double somaValorItensPedido(Integer pedidoId) throws DAOException {
        try {
            PreparedStatement st = connection.prepareStatement(SOMA_VALOR_ITENS_PEDIDO);
            st.setInt(1, pedidoId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("valor");
            }
            return 0.00;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar valor do pedido " + pedidoId + " | " + e);
        }
    }
}
