package com.ufpr.tads.web2.beans;

import java.sql.Time;

public class Roupa {
    private int id;
    private String nome;
    private Double preco;
    private Time prazo;

    public Roupa() {
    }

    public Roupa(int id, String nome, Double preco, Time prazo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.prazo = prazo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Time getPrazo() {
        return prazo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setPrazo(Time prazo) {
        this.prazo = prazo;
    }
    
    
}
