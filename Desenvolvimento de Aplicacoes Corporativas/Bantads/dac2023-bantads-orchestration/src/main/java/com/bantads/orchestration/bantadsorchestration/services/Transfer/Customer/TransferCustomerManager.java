package com.bantads.orchestration.bantadsorchestration.services.Transfer.Customer;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;

public class TransferCustomerManager {
    private RabbitCustomerManagerDTO customer;
    private String action;

    public TransferCustomerManager() {
    }

    public TransferCustomerManager(RabbitCustomerManagerDTO customer, String action) {
        this.customer = customer;
        this.action = action;
    }

	public RabbitCustomerManagerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(RabbitCustomerManagerDTO customer) {
		this.customer = customer;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
