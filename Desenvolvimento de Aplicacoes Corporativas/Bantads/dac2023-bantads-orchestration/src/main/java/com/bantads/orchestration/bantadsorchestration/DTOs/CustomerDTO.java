package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.math.BigDecimal;

public class CustomerDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String confirmarSenha;
    private AddressDTO endereco;
    private BigDecimal salario;
    private UserDTO usuario;

    public CustomerDTO() {
    }

    public CustomerDTO(String nome, String cpf, String email, String senha, String confirmarSenha, String telefone, AddressDTO endereco, BigDecimal salario, UserDTO usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.salario = salario;
        this.usuario = usuario;
    }

    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public AddressDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(AddressDTO endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
