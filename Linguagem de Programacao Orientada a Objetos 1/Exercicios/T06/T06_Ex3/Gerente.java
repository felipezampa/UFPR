/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 3
 */
package T06_Ex3;

public class Gerente extends Funcionario{
    
    private final String[] Funcionarios;
    
    public Gerente(String nome, String endereco, int idade, int cpf, double salario, String[] Funcionarios) {
        super(nome, endereco, idade, cpf, salario);
        this.Funcionarios = Funcionarios;
    }
    
    @Override
    public double getBonus() {
        return (2 * super.getSalario()) + (100 * Funcionarios.length);
    }
    
}
