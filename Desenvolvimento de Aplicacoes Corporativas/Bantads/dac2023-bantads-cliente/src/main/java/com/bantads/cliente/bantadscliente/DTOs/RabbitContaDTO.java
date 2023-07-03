package com.bantads.cliente.bantadscliente.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public class RabbitContaDTO {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private BigDecimal salario;

    public RabbitContaDTO() {
    }

    public RabbitContaDTO(String string, String nome, String cpf, String email, BigDecimal salario) {
        super();
        this.id = string;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.salario = salario;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
