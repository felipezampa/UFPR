package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String cpf;
	private BigDecimal salario;

	public UserDTO() {
		super();
	}
	
	public UserDTO(String id, String cpf, BigDecimal salario) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.salario = salario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}
