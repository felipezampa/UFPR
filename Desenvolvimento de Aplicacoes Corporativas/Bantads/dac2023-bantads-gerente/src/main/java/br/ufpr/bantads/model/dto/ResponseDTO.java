package br.ufpr.bantads.model.dto;

public class ResponseDTO {

	private GerenteResponseDTO manager;
	private String action;

	public GerenteResponseDTO getManager() {
		return manager;
	}

	public void setManager(GerenteResponseDTO manager) {
		this.manager = manager;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ResponseDTO(GerenteResponseDTO manager, String action) {
		super();
		this.manager = manager;
		this.action = action;
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
