/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 3
 */
package T06_Ex3;

public class Analista extends Funcionario{
    
    public Analista(String nome, String endereco, int idade, int cpf, double salario) {
        super(nome, endereco, idade, cpf, salario);
    }
    
    @Override
    public double getBonus() {
        return super.getSalario();
    }
    
}

