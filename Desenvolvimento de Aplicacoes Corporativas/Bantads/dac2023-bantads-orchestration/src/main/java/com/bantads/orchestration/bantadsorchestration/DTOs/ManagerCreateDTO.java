package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.bantads.orchestration.bantadsorchestration.model.Address;

public class ManagerCreateDTO implements Serializable {
    private String id;
    private String nome;
    private String email;
    private String password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ManagerCreateDTO(String id, String nome, String email, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

	

}
