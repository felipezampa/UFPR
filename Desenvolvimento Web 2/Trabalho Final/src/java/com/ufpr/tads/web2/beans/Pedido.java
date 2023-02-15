package com.ufpr.tads.web2.beans;

import com.ufpr.tads.web2.enums.SituacaoPedido;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.RoupaDAO;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.SQLException;
import java.util.Date;
import javax.persistence.Transient;

public class Pedido {
    private int id;
    private int usuario;
    private Double valorPedido;
    private Date dataPedido;
    private Date dataPagamento;
    private SituacaoPedido situacaoPedido;

    public Pedido() {
    }

    public Pedido(int id, int usuario, Double valorPedido, Date dataPedido, SituacaoPedido situacaoPedido) {
        this.id = id;
        this.usuario = usuario;
        this.valorPedido = valorPedido;
        this.dataPedido = dataPedido;
        this.situacaoPedido = situacaoPedido;
    }

    public int getId() {
        return id;
    }

    public int getUsuario() {
        return usuario;
    }

    public Double getValorPedido() {
        return valorPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public SituacaoPedido getSituacaoPedido() {
        return situacaoPedido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
        this.situacaoPedido = situacaoPedido;
    }
    
    @Transient
    public String getPrazoPedido() throws DAOException {
        try {
            ConnectionFactory con = new ConnectionFactory();
            RoupaDAO roupaDAO = new RoupaDAO(con.getConnection());
            String prazoRoupa = roupaDAO.getPrazoPedido();
            
            return prazoRoupa;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar prazo" + ex);
        }
    }
}
