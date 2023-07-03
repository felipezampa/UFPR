package com.bantads.orchestration.bantadsorchestration.DTOs;

import com.bantads.orchestration.bantadsorchestration.model.State;

public class AddressDTO {
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private State estado;

    public AddressDTO() {
    }

    public AddressDTO(String cep, String logradouro, int numero, String complemento, String bairro, String cidade,
            State estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

}
