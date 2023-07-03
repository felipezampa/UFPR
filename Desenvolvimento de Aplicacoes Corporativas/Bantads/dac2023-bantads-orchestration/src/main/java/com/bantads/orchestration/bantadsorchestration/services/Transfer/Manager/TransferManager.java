package com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerManagerDTO;

public class TransferManager {
    private RabbitCustomerManagerDTO manager;
    private String action;

    public TransferManager() {
    }

    public TransferManager(RabbitCustomerManagerDTO manager, String action) {
        this.manager = manager;
        this.action = action;
    }

	public RabbitCustomerManagerDTO getManager() {
		return manager;
	}

	public void setManager(RabbitCustomerManagerDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
