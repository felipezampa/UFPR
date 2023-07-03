package com.bantads.orchestration.bantadsorchestration.services.Transfer.Account;

import java.util.UUID;


public class TransferDeleteManagerAccount {
    private UUID id;
    private String action;

    public TransferDeleteManagerAccount() {
    }

    public TransferDeleteManagerAccount(UUID id, String action) {
        this.id = id;
        this.action = action;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
