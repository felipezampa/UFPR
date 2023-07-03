package com.bantads.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auth")
public class Usuario {
  @Id
  private String id;

  @NotBlank
  @Size(max = 120)
  private String nome;

  @NotBlank
  @Size(max = 120)
  private String email;

  @NotBlank
  @Size(max = 120)
  private String senha;

  @NotBlank
  @Size(max = 120)
  private String perfil;

  @NotBlank
  @Size(max = 120)
  private String salt;


  public Usuario() {
  }

	public Usuario(String id, String nome, String email, String senha, String perfil, String salt) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
		this.salt = salt;
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

	public String getEmail() {
		return email;
	}

	public void setLogin(String login) {
		this.email = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
 //criar uma função pra salvar no banco
}