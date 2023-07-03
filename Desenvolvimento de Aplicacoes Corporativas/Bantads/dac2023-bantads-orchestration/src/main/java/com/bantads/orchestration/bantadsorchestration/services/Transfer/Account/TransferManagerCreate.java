package com.bantads.orchestration.bantadsorchestration.services.Transfer.Account;

import java.io.Serializable;

import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerCreateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;

public class TransferManagerCreate implements Serializable  {
    private ManagerCreateDTO manager;
    private String action;

    public TransferManagerCreate() {
    }

    public TransferManagerCreate(ManagerCreateDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	public ManagerCreateDTO getManager() {
		return manager;
	}

	public void setManager(ManagerCreateDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
