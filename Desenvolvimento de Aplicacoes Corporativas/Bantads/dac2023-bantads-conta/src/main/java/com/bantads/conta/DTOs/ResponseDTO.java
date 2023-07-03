package com.bantads.conta.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ResponseDTO implements Serializable {

	private ResponseUserDTO manager;
	private String action;

	public ResponseUserDTO getManager() {
		return manager;
	}

	public void setManager(ResponseUserDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ResponseDTO(ResponseUserDTO responseUser, String action) {
		super();
		this.manager = responseUser;
		this.action = action;
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
