package com.bantads.auth.model;

import java.io.Serializable;

public class Auth implements Serializable{
	private String login;
	private String senha;

	public Auth() {
	super();
	}

	public Auth(String login, String senha) {
	super();
	this.login = login;
	this.senha = senha;
	}
	// setters/getters

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}