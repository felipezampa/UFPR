package com.bantads.orchestration.bantadsorchestration.model;

import java.io.Serializable;
import java.util.UUID;

public class Address implements Serializable {
    private UUID id;
    private String cep;
    private String publiccomplement;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private State state;

    public Address() {
        super();
    }

    public Address(UUID id, String cep, String publiccomplement, int number, String complement, String neighborhood,
            String city, State state) {
        super();
        this.id = id;
        this.cep = cep;
        this.publiccomplement = publiccomplement;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return publiccomplement;
    }

    public void setLogradouro(String publiccomplement) {
        this.publiccomplement = publiccomplement;
    }

    public int getNumero() {
        return number;
    }

    public void setNumero(int number) {
        this.number = number;
    }

    public String getComplemento() {
        return complement;
    }

    public void setComplemento(String complement) {
        this.complement = complement;
    }

    public String getBairro() {
        return neighborhood;
    }

    public void setBairro(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCidade() {
        return city;
    }

    public void setCidade(String city) {
        this.city = city;
    }

    public State getEstado() {
        return state;
    }

    public void setEstado(State state) {
        this.state = state;
    }

}
