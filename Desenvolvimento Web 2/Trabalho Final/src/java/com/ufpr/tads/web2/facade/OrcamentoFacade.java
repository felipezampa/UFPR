/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.PedidoDAO;
import com.ufpr.tads.web2.enums.SituacaoPedido;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.PedidoException;

import java.sql.SQLException;
import java.util.*;

public class OrcamentoFacade {

    public static void aprovarOrcamento(Integer id) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            dao.atualizarSituacao(pedido, SituacaoPedido.EM_ABERTO);

        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao aprovar orcamento " + id, e);
        }
    }

    public static void rejeitarOrcamento(Integer id) throws PedidoException {
        try ( ConnectionFactory conn = new ConnectionFactory()) {
            PedidoDAO dao = new PedidoDAO(conn.getConnection());
            Pedido pedido = dao.buscar(id);

            dao.atualizarSituacao(pedido, SituacaoPedido.REJEITADO);

        } catch (SQLException | DAOException e) {
            throw new PedidoException("Erro ao rejeitar orcamento " + id, e);
        }
    }
}
