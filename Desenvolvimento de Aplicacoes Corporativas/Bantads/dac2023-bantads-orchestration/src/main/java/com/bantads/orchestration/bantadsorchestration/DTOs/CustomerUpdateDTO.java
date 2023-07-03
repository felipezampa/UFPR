package com.bantads.orchestration.bantadsorchestration.DTOs;

import java.math.BigDecimal;

public class CustomerUpdateDTO {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private AddressDTO endereco;
    private BigDecimal salario;
    private UserDTO usuario;

    public CustomerUpdateDTO() {
    }

    
    public String getTelefone() {
		return telefone;
	}


	public CustomerUpdateDTO(String id, String nome, String cpf, String email, String telefone, AddressDTO endereco,
			BigDecimal salario, UserDTO usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.salario = salario;
		this.usuario = usuario;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public CustomerUpdateDTO(String id, String nome, String cpf, String email, String senha, String confirmarSenha, AddressDTO endereco, BigDecimal salario, UserDTO usuario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public AddressDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(AddressDTO endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
