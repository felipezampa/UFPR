package com.bantads.auth.DTO;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

public class UsuarioDTO {
  private String id;

   private String nome;

  
  private String email;

  
  private String perfil;

  

  public UsuarioDTO() {
  }

	public UsuarioDTO(String id, String nome, String email, String senha, String perfil, String salt) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
	}

		public UsuarioDTO(String id, String nome, String email, String perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
 //criar uma função pra salvar no banco
}