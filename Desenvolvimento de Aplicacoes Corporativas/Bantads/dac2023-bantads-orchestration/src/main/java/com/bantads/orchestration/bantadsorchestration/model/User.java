package com.bantads.orchestration.bantadsorchestration.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private UUID id;
    private String email;
    private String password;
    private UserType userType;
    private UUID saga;

    public User() {
        super();
    }

    public User(UUID id, String password, String email, UserType userType, UUID saga) {
        super();
        this.id = id;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.saga = saga;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenha() {
        return password;
    }

    public void setSenha(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getTipoUsuario() {
        return userType;
    }

    public void setTipoUsuario(UserType userType) {
        this.userType = userType;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

}
