package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {

    private int id;
    private String cpf;
    private String nome;
    private String email;
    private Date data;
    private String rua;
    private int numero;
    private String cep;
    private String cidade;
    private String uf;

    public Cliente() {
    }

    public Cliente(int id, String cpf, String nome, String email, Date data, String rua, int numero, String cep, String cidade, String uf) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.data = data;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}