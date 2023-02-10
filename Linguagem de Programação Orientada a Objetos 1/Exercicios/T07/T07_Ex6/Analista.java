/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 6
 */
package T07_Ex6;

public class Analista extends Funcionario {
    
    public Analista(String nome, String endereco, int idade, int cpf, double salario) {
        super(nome, endereco, idade, cpf, salario);
    }
    
    public double getBonus() {
        return super.getSalario();
    }
}
