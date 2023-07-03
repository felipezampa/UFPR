package com.bantads.orchestration.bantadsorchestration.services.Transfer.Account;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;

public class TransferManagerAccount {
    private RabbitManagerDTO manager;
    private String action;

    public TransferManagerAccount() {
    }

    public TransferManagerAccount(RabbitManagerDTO manager, String action) {
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
