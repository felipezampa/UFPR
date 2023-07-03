package com.bantads.conta.model.R;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_movimentacao")

public class MovimentacaoR implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;

	@Column(name="tipo_movimentacao")
	private int tipoMovimentacao;
	
	@Column(name="valor")
	private BigDecimal valor;

	@Column(name="data_hora_criacao")
	private LocalDateTime dataHoraCriacao;

	@Column(name="numero_conta_origem")
    private Long contaOrigem;
	
	@Column(name="numero_conta_destino")
    private Long contaDestino;

	public MovimentacaoR() {
		super();
	}

	public MovimentacaoR(Long id, int tipoMovimentacao, BigDecimal valor, LocalDateTime dataHoraCriacao, Long contaOrigem,
			Long contaDestino) {
		super();
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.dataHoraCriacao = dataHoraCriacao;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(int tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public Long getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Long contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Long getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Long contaDestino) {
		this.contaDestino = contaDestino;
	}

	@Override
	public String toString() {
		return "MovimentacaoR [id=" + id + ", tipoMovimentacao=" + tipoMovimentacao + ", valor=" + valor
				+ ", dataHoraCriacao=" + dataHoraCriacao + ", contaOrigem=" + contaOrigem + ", contaDestino="
				+ contaDestino + "]";
	}

}
