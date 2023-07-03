package com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;

public class TransferDeleteAuth implements Serializable {
    private CustomerDeleteDTO manager;
    private String action;


    
    public TransferDeleteAuth(CustomerDeleteDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	public CustomerDeleteDTO getManager() {
		return manager;
	}

	public void setManager(CustomerDeleteDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
