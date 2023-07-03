package com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitUserDTO;

public class TransferAuth implements Serializable {
    private RabbitCustomerDTO manager;
    private String action;


    
    public TransferAuth(RabbitCustomerDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	public TransferAuth(CustomerDeleteDTO customer, String action2) {
		// TODO Auto-generated constructor stub
	}

	public RabbitCustomerDTO getManager() {
		return manager;
	}

	public void setManager(RabbitCustomerDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
