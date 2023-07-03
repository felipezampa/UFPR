package com.bantads.conta.model.R;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_conta")

public class ContaR implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="numero")
	private Long numero;
	
	@Column(name="id_cliente")
	private String idCliente;
	
	@Column(name="id_gerente")
	private String idGerente;
	
	@Column(name="saldo")
	private BigDecimal saldo;
	
	@Column(name="data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name="limite")
	private BigDecimal limite;
	
	public ContaR() {
		super();
	}

	public ContaR(String idCliente, String idGerente, Long numero, BigDecimal saldo, LocalDate dataCriacao, BigDecimal limite) {
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

	public String getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(String idGerente) {
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
