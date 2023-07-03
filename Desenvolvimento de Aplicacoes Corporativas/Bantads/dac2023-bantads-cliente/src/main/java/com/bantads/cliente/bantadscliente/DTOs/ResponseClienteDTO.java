package com.bantads.cliente.bantadscliente.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

import com.bantads.cliente.bantadscliente.model.Endereco;

public class ResponseClienteDTO {
	private String id;
	private String nome;
	private String cpf;
	private ResponseEnderecoDTO endereco;
	private BigDecimal salario;
	private String email;
	private String telefone;

	public ResponseClienteDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ResponseEnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(ResponseEnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
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

	public ResponseClienteDTO(String id, String nome, String cpf, ResponseEnderecoDTO endereco, BigDecimal salario,
			String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.salario = salario;
		this.email = email;
		this.telefone = telefone;
	}

}
