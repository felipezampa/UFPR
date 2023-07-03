package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public class ResponseClienteSagaDTO {
	private UUID id;
	private String nome;
	private String email;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
}
