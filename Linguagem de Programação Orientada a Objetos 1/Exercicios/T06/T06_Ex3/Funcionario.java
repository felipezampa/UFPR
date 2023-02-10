/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 3
 */
package T06_Ex3;

public abstract class Funcionario {
    private String nome;
    private String endereco;
    private int idade;
    private int cpf;
    private double salario;

    public Funcionario(String nome, String endereco, int idade, int cpf, double salario) {
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
        this.cpf = cpf;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public abstract double getBonus();
}
