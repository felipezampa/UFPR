package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idCliente;
	private String idGerente;
	private BigDecimal saldo;
	private BigDecimal limite;

	public ContaDTO() {
		super();
	}

	public ContaDTO(String idCliente, String idGerente, BigDecimal saldo, BigDecimal limite) {
		super();
		this.idCliente = idCliente;
		this.idGerente = idGerente;
		this.saldo = saldo;
		this.limite = limite;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(String idGerente) {
		this.idGerente = idGerente;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
}
