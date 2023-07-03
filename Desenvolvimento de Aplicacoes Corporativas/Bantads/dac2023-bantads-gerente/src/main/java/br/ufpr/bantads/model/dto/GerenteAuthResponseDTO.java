package br.ufpr.bantads.model.dto;

import java.io.Serializable;

public class GerenteAuthResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private GerenteAuthDTO gerente;
    private String action;

    public GerenteAuthResponseDTO() {
    }

    public GerenteAuthResponseDTO(GerenteAuthDTO gerente, String action) {
        this.gerente = gerente;
        this.action = action;
    }

    public GerenteAuthDTO getGerente() {
        return gerente;
    }

    public void setGerente(GerenteAuthDTO gerente) {
        this.gerente = gerente;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
