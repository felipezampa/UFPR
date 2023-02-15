package com.ufpr.tads.web2.beans;

public class LoginBean {

    private int idUsuario;
    private String nomeUsuario;
    private boolean funcionario;

    public LoginBean() {
    }

    public LoginBean(int idUsuario, String nomeUsuario, boolean funcionario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.funcionario = funcionario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }
    
}
