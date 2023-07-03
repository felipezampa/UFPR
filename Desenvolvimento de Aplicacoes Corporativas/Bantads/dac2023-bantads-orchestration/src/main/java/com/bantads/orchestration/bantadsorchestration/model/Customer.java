package com.bantads.orchestration.bantadsorchestration.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class Customer implements Serializable {
    private UUID id;
    private UUID idExternalUser;
    private String name;
    private String cpf;
    private Address address;
    private BigDecimal salary;
    private UUID saga;

    public Customer() {
        super();
    }

    public Customer(UUID id, UUID idExternalUser, String name, String cpf, Address address, BigDecimal salary,
            UUID saga) {
        super();
        this.id = id;
        this.idExternalUser = idExternalUser;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.salary = salary;
        this.saga = saga;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdExternoUsuario() {
        return idExternalUser;
    }

    public void setIdExternoUsuario(UUID idExternalUser) {
        this.idExternalUser = idExternalUser;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getEndereco() {
        return address;
    }

    public void setEndereco(Address address) {
        this.address = address;
    }

    public BigDecimal getSalario() {
        return salary;
    }

    public void setSalario(BigDecimal salary) {
        this.salary = salary;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

}
