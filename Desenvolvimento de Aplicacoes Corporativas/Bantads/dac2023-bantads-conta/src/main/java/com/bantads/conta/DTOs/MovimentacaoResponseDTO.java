package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private int tipoMovimentacao;
	private LocalDateTime dataHoraCriacao;
	private BigDecimal valor;
	private Long contaOrigem;
    private Long contaDestino;

	public MovimentacaoResponseDTO() {
		super();
	}

	public MovimentacaoResponseDTO(Long id, int tipoMovimentacao, LocalDateTime dataHoraCriacao, BigDecimal valor,
			Long contaOrigem, Long contaDestino) {
		super();
		this.id = id;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataHoraCriacao = dataHoraCriacao;
		this.valor = valor;
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

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
}
