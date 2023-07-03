package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.io.Serializable;
import java.util.UUID;
import com.bantads.orchestration.bantadsorchestration.model.UserType;


public class RabbitUserDTO implements Serializable {
    private UUID id;
    private String email;
    private String senha;
    private UserType tipoUsuario;
    private UUID saga;

    public RabbitUserDTO() {
        super();
    }

    public RabbitUserDTO(UUID id, String senha, String email, UserType tipoUsuario, UUID saga) {
        super();
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.saga = saga;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UserType getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(UserType tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

}
