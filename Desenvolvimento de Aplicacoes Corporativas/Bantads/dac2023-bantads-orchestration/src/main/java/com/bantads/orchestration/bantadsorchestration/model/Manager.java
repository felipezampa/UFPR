package com.bantads.orchestration.bantadsorchestration.model;

import java.io.Serializable;
import java.util.UUID;

public class Manager implements Serializable {
    private UUID id;
    private UUID idExternalUser;
    private String name;
    private String cpf;
    private UUID saga;

    public Manager() {
        super();
    }

    public Manager(UUID id, UUID idExternalUser, String name, String cpf, UUID saga) {
        super();
        this.id = id;
        this.idExternalUser = idExternalUser;
        this.name = name;
        this.cpf = cpf;
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

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

}
