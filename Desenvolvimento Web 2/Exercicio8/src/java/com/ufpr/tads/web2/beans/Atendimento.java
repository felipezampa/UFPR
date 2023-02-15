package com.ufpr.tads.web2.beans;

import java.util.Date;

public class Atendimento {
    
    private int id;
    private Date data;
    private String nome;
    private String descricao;
    private Produto produto;
    private TipoAtendimento tipoAtendimento;
    private Usuario usuario;
    private Cliente cliente;
    private String resposta;

    public Atendimento() {
    }

    public Atendimento(int id, Date data, String nome, String descricao, Produto produto, TipoAtendimento tipoAtendimento, Usuario usuario, Cliente cliente, String resposta) {
        this.id = id;
        this.data = data;
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
        this.tipoAtendimento = tipoAtendimento;
        this.usuario = usuario;
        this.cliente = cliente;
        this.resposta = resposta;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getResposta() {
        return resposta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


    
    
}
