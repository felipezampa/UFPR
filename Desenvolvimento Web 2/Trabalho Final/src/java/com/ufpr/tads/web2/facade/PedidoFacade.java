/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.dao.*;
import com.ufpr.tads.web2.enums.SituacaoPedido;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.PedidoException;

import java.sql.SQLException;
import java.util.*;

public class PedidoFacade {

    public static List<Pedido> getPedidosPorUsuarioESituacao(Integer usuarioId, SituacaoPedido situacao) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarListaPorUsuarioESituacao(usuarioId, situacao);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos na situação " + situacao.getDescricao() + " para o usuario " + usuarioId, e);
        }
    }

    public static void cancelarPedido(Integer id) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            dao.atualizarSituacao(pedido, SituacaoPedido.CANCELADO);

        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao cancelar pedido " + id, e);
        }
    }

    public static List<Pedido> buscarTodosPorCliente(Integer usuarioId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarPorUsuario(usuarioId);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos para o usuario " + usuarioId, e);
        }
    }
    
        public static List<Pedido> buscarClienteNumPedido(Integer usuarioId, Integer idPedido) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarUsarioNumPedido(usuarioId, idPedido);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos para o usuario " + usuarioId, e);
        }
    }

    public static Pedido buscarAguardandoOrcamentoOuCriarNovo(Integer usuarioId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscarPorUsuarioESituacao(usuarioId, SituacaoPedido.AGUARDANDO_ORCAMENTO);

            if (pedido == null) {
                pedido = criarPedido(usuarioId);
            }

            return pedido;
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedido em orçamento para o usuario " + usuarioId, e);
        }
    }

    private static Pedido criarPedido(Integer usuarioId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuarioId);
            pedido.setValorPedido(0.00);
            pedido.setDataPedido(new Date());
            pedido.setSituacaoPedido(SituacaoPedido.AGUARDANDO_ORCAMENTO);
            return dao.inserir(pedido);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao criar pedido para o usuario " + usuarioId, e);
        }
    }

    public static void solicitarOrcamento(Integer id) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            Double valorPedido = ItensPedidoFacade.somaValorItensPedido(id);
            dao.atualizarValor(pedido, valorPedido);
            dao.atualizarSituacao(pedido, SituacaoPedido.AGUARDANDO_APROVACAO);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao enviar pedido " + id, e);
        }
    }

    public static List<Pedido> getPedidoBuscaOrderDate() throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarSituacaoOderDate();
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos em aberto" + e);
        }
    }

    public static List<Pedido> getPedidoAll() throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarPedidosAll();
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos em aberto" + e);
        }
    }

    public static List<Pedido> getBuscaPedidoHoje() throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarPedidosDeHoje();
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos em aberto" + e);
        }
    }

    public static void alteraSituacao(Integer id, SituacaoPedido situacao) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            dao.atualizarSituacao(pedido, situacao);

        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao cancelar pedido " + id, e);
        }
    }

    public static void atualizaDataPagamento(Integer id, Date data) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            dao.atualizaDataPagamento(pedido.getId(), data);

        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao cancelar pedido " + id, e);
        }
    }

    public static List<Pedido> getBuscaPedidoDataInFin(Date Inicial, Date Final) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            return dao.buscarPedidosIntervaloData(Inicial, Final);
        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao buscar pedidos em aberto" + e);
        }
    }

}
