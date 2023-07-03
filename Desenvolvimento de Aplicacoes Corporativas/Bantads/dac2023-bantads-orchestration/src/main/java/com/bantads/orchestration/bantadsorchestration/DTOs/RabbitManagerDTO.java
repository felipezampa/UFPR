package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.io.Serializable;
import java.util.UUID;

public class RabbitManagerDTO implements Serializable {
    private UUID id;
    private UUID idExternoUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private UUID saga;

    public RabbitManagerDTO() {
        super();
    }

    public RabbitManagerDTO(UUID id, UUID idExternoUsuario, String nome, String email, String telefone, String cpf, UUID saga) {
        super();
        this.id = id;
        this.idExternoUsuario = idExternoUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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
        return idExternoUsuario;
    }

    public void setIdExternoUsuario(UUID idExternoUsuario) {
        this.idExternoUsuario = idExternoUsuario;
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

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
