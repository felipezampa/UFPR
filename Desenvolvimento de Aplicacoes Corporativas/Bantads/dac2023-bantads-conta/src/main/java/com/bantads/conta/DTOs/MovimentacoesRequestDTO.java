package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimentacoesRequestDTO implements Serializable  {
	private static final long serialVersionUID = 1L;

	private String dataInicio;
	private String dataFim; 
    private String idCliente;

	public MovimentacoesRequestDTO() {
		super();
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MovimentacoesRequestDTO(String dataInicio, String dataFim, String id) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idCliente = id;
	}

	
}
