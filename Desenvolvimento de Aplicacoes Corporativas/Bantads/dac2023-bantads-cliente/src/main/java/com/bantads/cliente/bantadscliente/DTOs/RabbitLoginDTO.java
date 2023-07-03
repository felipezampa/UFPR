package com.bantads.cliente.bantadscliente.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public class RabbitLoginDTO {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public RabbitLoginDTO() {
    }

    public RabbitLoginDTO(String string, String nome, String cpf, String email, String senha) {
        super();
        this.id = string;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
