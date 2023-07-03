package com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitUserDTO;

public class TransferAuthManager implements Serializable {
    private RabbitManagerDTO manager;
    private String action;


    
    public TransferAuthManager(RabbitManagerDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	public RabbitManagerDTO getManager() {
		return manager;
	}

	public void setManager(RabbitManagerDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
