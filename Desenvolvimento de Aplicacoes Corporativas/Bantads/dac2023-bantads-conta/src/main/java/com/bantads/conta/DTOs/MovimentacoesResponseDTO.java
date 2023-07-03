package com.bantads.conta.DTOs;

import java.util.List;

import com.bantads.conta.model.R.MovimentacaoR;

public class MovimentacoesResponseDTO {
	private List<MovimentacaoR> movimentacoes;
	
	public MovimentacoesResponseDTO(List<MovimentacaoR> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public List<MovimentacaoR> getMovimentacoes() {
		return this.movimentacoes;
	}
	
	public void setMovimentacoes(List<MovimentacaoR> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
}
