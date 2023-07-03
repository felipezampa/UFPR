package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimentacaoDTO implements Serializable  {
	private static final long serialVersionUID = 1L;

	private int tipoMovimentacao;
	private BigDecimal valor;
	private Long contaOrigem;
    private Long contaDestino;

	public MovimentacaoDTO() {
		super();
	}

	public MovimentacaoDTO( int tipoMovimentacao, BigDecimal valor, Long contaOrigem, Long contaDestino) {
		super();
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
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
