package com.ufpr.tads.web2.beans;

import com.ufpr.tads.web2.utils.DateUtils;
import java.util.Date;

public class Usuario {

    private int id;          
    private String cpf; 
    private String login; 
    private String senha;
    private String nome;
    private String cep;
    private String numero;
    private String telefone;
    private Date dataNascimento;
    private boolean funcionario;  

    public Usuario() {
    }

    public Usuario(int id, String cpf, String login, String senha, String nome, String cep, String numero, String telefone, Date dataNascimento, boolean funcionario) {
        this.id = id;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cep = cep;
        this.numero = numero;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoFormatada() {
        return DateUtils.formatDate(dataNascimento);
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }
    
}
