package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.math.BigDecimal;

public class CustomerDeleteDTO {
    private String id;

    public CustomerDeleteDTO() {
    }

    public CustomerDeleteDTO(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
