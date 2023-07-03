package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idCliente;
	private String idGerente;
	private Long numero;
	private BigDecimal saldo;
	private LocalDate dataCriacao;
	private BigDecimal limite;
	
	public ContaResponseDTO() {
		super();
	}

	public ContaResponseDTO(String idCliente, String idGerente, Long numero, BigDecimal saldo, LocalDate dataCriacao,
			BigDecimal limite) {
		super();
		this.idCliente = idCliente;
		this.idGerente = idGerente;
		this.numero = numero;
		this.saldo = saldo;
		this.dataCriacao = dataCriacao;
		this.limite = limite;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getidGerente() {
		return idGerente;
	}

	public void setidGerente(String idGerente) {
		this.idGerente = idGerente;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
}
