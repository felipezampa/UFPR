package br.ufpr.bantads.model.dto;


import java.io.Serializable;

public class GerenteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String cpf;
	private String email;
	private String phone;
	private int numClientes;

	public GerenteDTO() {
	}

	public GerenteDTO(String id, String nome, String cpf, String email, String phone, int numClientes) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.phone = phone;
		this.numClientes = numClientes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(int numClientes) {
		this.numClientes = numClientes;
	}
}
