package com.bantads.cliente.bantadscliente.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    private String id;
    private String nome;
    private String cpf;
    private Endereco endereco;
    private BigDecimal salario;
    private String senha;
    private String email;
    private UUID saga;
    private String telefone;

    public Cliente() {
        super();
    }

    
    public Cliente(String id, String nome, String cpf, Endereco endereco, BigDecimal salario, String senha,
			String email, UUID saga, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.salario = salario;
		this.senha = senha;
		this.email = email;
		this.saga = saga;
		this.telefone = telefone;
	}

    @Column(name = "telefone")
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Cliente(String id, String nome, String cpf, Endereco endereco,
			BigDecimal salario, String senha, String email, UUID saga) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.salario = salario;
		this.senha = senha;
		this.email = email;
		this.saga = saga;
	}




	@Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idendereco", referencedColumnName = "id")
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Column(name = "salario")
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Column(name = "saga")
    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }
    
    @Column(name = "senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
