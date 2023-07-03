package com.bantads.auth.DTO;

import java.io.Serializable;

public class AuthDTO implements Serializable{
	private String email;
	private String senha;

	public AuthDTO() {
	super();
	}

	public AuthDTO(String email, String senha) {
	super();
	this.email = email;
	this.senha = senha;
	}
	// setters/getters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}