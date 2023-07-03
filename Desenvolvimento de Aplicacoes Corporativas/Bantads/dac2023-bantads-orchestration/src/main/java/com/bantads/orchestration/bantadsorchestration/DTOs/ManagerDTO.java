package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

import com.bantads.orchestration.bantadsorchestration.model.Address;

public class ManagerDTO {
	private UUID id;
	private UUID idExternoUsuario;
	private String nome;
	private String cpf;
	private String email;
	private Address endereco;
	private String senha;
	private BigDecimal salario;
	private UUID saga;

	public ManagerDTO() {
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

	public Address getEndereco() {
		return endereco;
	}

	public void setEndereco(Address endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
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

}
