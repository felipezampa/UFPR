package com.bantads.orchestration.bantadsorchestration.services.Transfer.Customer;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;

public class TransferConsumerCustomer {
    private RabbitManagerDTO customer;
    private String action;

    public TransferConsumerCustomer() {
    }

    public TransferConsumerCustomer(RabbitManagerDTO manager, String action) {
        this.customer = manager;
        this.action = action;
    }

	public RabbitManagerDTO getManager() {
		return customer;
	}

	public void setManager(RabbitManagerDTO manager) {
		this.customer = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
