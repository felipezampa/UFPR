package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.dao.*;
import com.ufpr.tads.web2.exceptions.*;

import java.sql.*;
import java.util.*;

public class ItensPedidoFacade {
    public static void adicionarItem(Integer pedidoId, Integer roupaId, Integer quantidade) throws com.ufpr.tads.web2.exceptions.PedidoException {
        if (quantidade <= 0) throw new PedidoException("Quantidade deve ser maior que zero");

        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ItensPedidoDAO dao = new ItensPedidoDAO(conn.getConnection());

            ItensPedido itensPedido = new ItensPedido();
            itensPedido.setPedidoId(pedidoId);
            itensPedido.setQuantidade(quantidade);

            Roupa roupa = new Roupa();
            roupa.setId(roupaId);
            itensPedido.setRoupa(roupa);

            dao.inserir(itensPedido);
        } catch (SQLException | com.ufpr.tads.web2.exceptions.DAOException e) {
            throw new PedidoException("Erro ao adicionar roupa " + roupaId + " ao pedido " + pedidoId, e);
        }
    }

    public static List<ItensPedido> buscarPorPedido(Integer pedidoId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ItensPedidoDAO dao = new ItensPedidoDAO(conn.getConnection());
            return dao.buscarPorPedido(pedidoId);
        } catch (SQLException | com.ufpr.tads.web2.exceptions.DAOException e) {
            throw new PedidoException("Erro ao buscar itens do pedido " + pedidoId, e);
        }
    }

    public static void removerItem(Integer itemId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ItensPedidoDAO dao = new ItensPedidoDAO(conn.getConnection());

            ItensPedido item = dao.buscar(itemId);
            dao.deletar(item);
        } catch (SQLException | com.ufpr.tads.web2.exceptions.DAOException e) {
            throw new PedidoException("Erro ao remover roupa " + itemId, e);
        }
    }

    public static Double somaValorItensPedido(Integer pedidoId) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            ItensPedidoDAO dao = new ItensPedidoDAO(conn.getConnection());
            return dao.somaValorItensPedido(pedidoId);
        } catch (SQLException | com.ufpr.tads.web2.exceptions.DAOException e) {
            throw new PedidoException("Erro ao buscar itens do pedido " + pedidoId, e);
        }
    }
}
