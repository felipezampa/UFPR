package br.ufpr.bantads.model.dto;

import java.io.Serializable;

public class GerenteResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public String id;
	public String idManager;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdManager() {
		return idManager;
	}

	public void setIdManager(String idManager) {
		this.idManager = idManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GerenteResponseDTO(String id, String idManager) {
		super();
		this.id = id;
		this.idManager = idManager;
	}

	public GerenteResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
