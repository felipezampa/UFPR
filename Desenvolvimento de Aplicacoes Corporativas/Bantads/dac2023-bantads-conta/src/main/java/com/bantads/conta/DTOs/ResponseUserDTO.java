package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ResponseUserDTO implements Serializable {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResponseUserDTO(String id) {
		super();
		this.id = id;
	}

	public ResponseUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
