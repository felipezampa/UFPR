package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.bantads.orchestration.bantadsorchestration.model.Address;

public class RabbitCustomerUpdateDTO implements Serializable {
	   private String id;
	    private String nome;
	    private String cpf;
	    private Address endereco;
	    private String senha;
	    private String email;
	    private BigDecimal salario;
	    private UUID saga;
	    
    public RabbitCustomerUpdateDTO() {
        super();
    }

    public RabbitCustomerUpdateDTO(String id, UUID idExternoUsuario, String nome, String cpf, Address endereco, String senha, BigDecimal salario,
            UUID saga) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.salario = salario;
        this.saga = saga;
    }

    public RabbitCustomerUpdateDTO(String id, String nome, String cpf, Address endereco, String senha, String email,
			BigDecimal salario, UUID saga) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.senha = senha;
		this.email = email;
		this.salario = salario;
		this.saga = saga;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }



}
