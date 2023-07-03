package br.ufpr.bantads.model.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "gerente")
public class Gerente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "numClientes")
    private int numClientes;

    public Gerente(Long id, String nome, String email, String cpf, String phone, int numClientes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.numClientes = numClientes;
    }

    public Gerente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumClientes() {
        return numClientes;
    }

    public void setNumClientes(int num_clientes) {
        this.numClientes = num_clientes;
    }

    public static Long getserialVersionUID() {
        return serialVersionUID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
