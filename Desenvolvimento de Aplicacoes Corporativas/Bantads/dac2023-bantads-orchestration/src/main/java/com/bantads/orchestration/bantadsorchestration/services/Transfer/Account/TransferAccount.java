package com.bantads.orchestration.bantadsorchestration.services.Transfer.Account;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitUserDTO;

public class TransferAccount implements Serializable {
    private RabbitCustomerDTO manager;
    private String action;


    
    public TransferAccount(RabbitCustomerDTO manager, String action) {
        this.manager = manager;
        this.action = action;
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
