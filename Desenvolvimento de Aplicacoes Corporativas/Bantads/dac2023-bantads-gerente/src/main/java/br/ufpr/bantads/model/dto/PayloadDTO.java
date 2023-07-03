package br.ufpr.bantads.model.dto;

import java.io.Serializable;

public class PayloadDTO implements Serializable {

    private UsuarioDTO manager;
    private String action;

    @Override
    public String toString() {
        return "PayloadDTO{" +
                "manager=" + manager +
                ", action='" + action + '\'' +
                '}';
    }

    public String getAction() {
        return action;
    }

	public UsuarioDTO getManager() {
		return manager;
	}

	public void setManager(UsuarioDTO manager) {
		this.manager = manager;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public PayloadDTO(UsuarioDTO manager, String action) {	
		super();
		this.manager = manager;
		this.action = action;
	}

	public PayloadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
