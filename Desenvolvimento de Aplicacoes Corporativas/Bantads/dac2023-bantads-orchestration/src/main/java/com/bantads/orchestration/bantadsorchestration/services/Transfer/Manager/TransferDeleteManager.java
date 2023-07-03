package com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager;

import java.util.UUID;


public class TransferDeleteManager {
    private UUID id;
    private String action;

    public TransferDeleteManager() {
    }

    public TransferDeleteManager(UUID id, String action) {
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
