package com.bantads.orchestration.bantadsorchestration.DTOs;

import com.bantads.orchestration.bantadsorchestration.model.UserType;

public class UserDTO {
    private String senha;
    private String email;
    private UserType tipoUsuario;

    public UserDTO() {
    }

    public UserDTO(String senha, String email, UserType tipoUsuario) {
        this.senha = senha;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
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

}
