package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ConfigBean implements Serializable {
    
    private String email = "felipeavzampa@ufpr.br";

    public ConfigBean() {
    }

    public String getEmail() {
        return email;
    }
       
}
