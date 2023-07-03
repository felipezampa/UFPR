package com.bantads.orchestration.bantadsorchestration.services.Transfer.Account;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitUserDTO;

public class TransferUpdateAccount implements Serializable {
    private RabbitCustomerUpdateDTO manager;
    private String action;


    
    public TransferUpdateAccount(RabbitCustomerUpdateDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	

	public RabbitCustomerUpdateDTO getManager() {
		return manager;
	}

	public void setManager(RabbitCustomerUpdateDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
