package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.bantads.orchestration.bantadsorchestration.model.Address;

public class RabbitCustomerManagerDTO implements Serializable {
    private String id;
    private String idManager;
    private String password;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdManager() {
		return idManager;
	}

	public void setIdManager(String idManager) {
		this.idManager = idManager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RabbitCustomerManagerDTO(String id, String idManager) {
		super();
		this.id = id;
		this.idManager = idManager;
	}

	public RabbitCustomerManagerDTO(String id, String idManager, String password) {
		super();
		this.id = id;
		this.idManager = idManager;
		this.password = password;
	}
	

}
