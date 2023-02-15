package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.enums.*;
import com.ufpr.tads.web2.exceptions.*;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class PedidoDAO {

    private static final String SELECT_ALL_FROM_PEDIDO = "SELECT id_pedido, usuario, valor_pedido, data_pedido, data_pagamento, situacao_pedido FROM pedido";
    private static final String BUSCAR = SELECT_ALL_FROM_PEDIDO + " WHERE id_pedido = ?;";
    private static final String BUSCAR_POR_USUARIO = SELECT_ALL_FROM_PEDIDO + " WHERE usuario = ? ORDER BY data_pedido desc;";
    private static final String BUSCAR_POR_USUARIO_E_SITUACAO = SELECT_ALL_FROM_PEDIDO + " WHERE usuario = ? AND situacao_pedido = ?;";
    private static final String INSERIR = "INSERT INTO pedido(usuario, valor_pedido, data_pedido, situacao_pedido) VALUES(?, ?, ?, ?);";
    private static final String INSERIR_ITEM_PEDIDO = "INSERT INTO itens_pedido(id_pedido, id_roupa, quantidade) VALUES(?, ?, ?);";
    private static final String ATUALIZAR_SITUACAO = "UPDATE pedido SET situacao_pedido = ? WHERE id_pedido = ?";
    private static final String ATUALIZAR_VALOR = "UPDATE pedido SET valor_pedido = ? WHERE id_pedido = ?";

    private static final String ATUALIZA_DATA_PAGAMENTO = "UPDATE pedido SET data_pagamento = ? WHERE id_pedido = ?";
    private static final String BUSCAR_EMABERTO_ODERDATE = "SELECT * FROM pedido p WHERE situacao_pedido = 'EM_ABERTO' order by data_pedido DESC";
    private static final String BUSCAR_PEDIDOHOJE = "SELECT * FROM  pedido p WHERE data_pedido = ? order by data_pedido ASC;";
    private static final String BUSCAR_PEDIDO_INTERVALODATA = "SELECT * FROM pedido p WHERE data_pedido between ? AND ? order by data_pedido ASC";
    private static final String BUSCAR_PEDIDO_ALL_ASC = SELECT_ALL_FROM_PEDIDO + " ORDER BY data_pedido ASC;";
    private static final String BUSCAR_POR_USUARIO_E_IDPEDIDO = SELECT_ALL_FROM_PEDIDO + " WHERE usuario = ? AND id_pedido=?;";
    
    private Connection connection = null;

    public PedidoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar UsuarioDAO.");
        }
        this.connection = con;
    }

    public Pedido buscar(Integer id) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            Pedido pedido = new Pedido();
            while (rs.next()) {
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
            }
            return pedido;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar pedido " + id + " | " + e);
        }
    }

    public List<Pedido> buscarPorUsuario(Integer usuarioId) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_USUARIO)) {
            st.setInt(1, usuarioId);

            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));

                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar pedidos do usuário " + usuarioId + " | " + e);
        }
    }

    public void atualizaDataPagamento(Integer idPedido, Date dataPagamento) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(ATUALIZA_DATA_PAGAMENTO)) {
            st.setDate(1, new java.sql.Date(dataPagamento.getTime()));
            st.setInt(2, idPedido);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao atualizar data de pagamento do pedido " + idPedido + " | " + e);
        }
    }
    public List<Pedido> buscarListaPorUsuarioESituacao(Integer usuarioId, SituacaoPedido situacao) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_USUARIO_E_SITUACAO)) {
            st.setInt(1, usuarioId);
            st.setString(2, situacao.toString());

            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));

                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar pedidos na situação " + situacao + " do usuário " + usuarioId + " | " + e);
        }
    }

    public Pedido buscarPorUsuarioESituacao(Integer usuarioId, SituacaoPedido situacaoPedido) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_USUARIO_E_SITUACAO)) {
            st.setInt(1, usuarioId);
            st.setString(2, situacaoPedido.toString());

            Pedido pedido = null;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
            }

            return pedido;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar pedidos do usuário " + usuarioId + " | " + e);
        }
    }

    public void atualizarSituacao(Pedido pedido, SituacaoPedido situacaoPedido) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(ATUALIZAR_SITUACAO)) {
            st.setString(1, situacaoPedido.toString());
            st.setInt(2, pedido.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao atualizar situação do pedido " + pedido.getId() + " | " + e);
        }
    }

    public Pedido inserir(Pedido pedido) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, pedido.getUsuario());
            st.setDouble(2, pedido.getValorPedido());
            st.setDate(3, new java.sql.Date(pedido.getDataPedido().getTime()));
            st.setString(4, pedido.getSituacaoPedido().toString());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
            return pedido;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao inserir pedido | " + e);
        }
    }

    public void inserirRoupa(Integer pedidoId, Integer roupaId, Integer quantidade) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR_ITEM_PEDIDO)) {
            st.setInt(1, pedidoId);
            st.setInt(2, roupaId);
            st.setInt(3, quantidade);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao inserir roupa " + roupaId + " no pedido " + pedidoId + " | " + e);
        }
    }

    public List<Pedido> buscarSituacaoOderDate() throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_EMABERTO_ODERDATE)) {
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));

                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar pedidos " + e);
        }
    }

    public List<Pedido> buscarPedidosAll() throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_PEDIDO_ALL_ASC)) {
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos os pedidos " + e);
        }
    }

    public List<Pedido> buscarPedidosDeHoje() throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_PEDIDOHOJE)) {
            Date data = new Date();
            st.setDate(1, new java.sql.Date(data.getTime()));
           
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos os pedidos " + e);
        }
    }
    
    public List<Pedido> buscarPedidosIntervaloData(Date Inicial, Date Final) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_PEDIDO_INTERVALODATA)) {

             st.setDate(1, new java.sql.Date(Inicial.getTime()));
             st.setDate(2, new java.sql.Date(Final.getTime()));
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos os pedidos " + e);
        }
    }
    
        public List<Pedido> buscarUsarioNumPedido(Integer idUsuario, Integer idPedido) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_POR_USUARIO_E_IDPEDIDO)) {

             st.setInt(1, idUsuario );
             st.setInt(2, idPedido);
            List<Pedido> pedidos = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setUsuario(rs.getInt("usuario"));
                pedido.setValorPedido(rs.getDouble("valor_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setDataPagamento(rs.getDate("data_pagamento"));
                pedido.setSituacaoPedido(SituacaoPedido.valueOf(rs.getString("situacao_pedido")));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos os pedidos " + e);
        }
    }

    public void atualizarValor(Pedido pedido, Double valor) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(ATUALIZAR_VALOR)) {
            st.setDouble(1, valor);
            st.setInt(2, pedido.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao atualizar valor do pedido " + pedido.getId() + " | " + e);
        }
    }
}
