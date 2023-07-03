package com.bantads.cliente.bantadscliente.DTOs;

import java.math.BigDecimal;

public class PostClienteDTO {
	private String nome;
	private String cpf;
	private String email;
	private PutEnderecoDTO endereco;
	private BigDecimal salario;
	private String senha;
	private String confirmarSenha;

	public PostClienteDTO() {
	}

	public PostClienteDTO(String nome, String cpf, String email, String senha, PutEnderecoDTO endereco, BigDecimal salario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.salario = salario;
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

	public PutEnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(PutEnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
