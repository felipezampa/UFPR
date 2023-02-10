/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 3
 */
package T06_Ex3;

public class Diretor extends Funcionario{
    
    private final String[] Departamentos;
    
    public Diretor(String nome, String endereco, int idade, int cpf, double salario, String[] Departamentos) {
        super(nome, endereco, idade, cpf, salario);
        this.Departamentos = Departamentos;
    }
    
    @Override
    public double getBonus() {
        return (4 * super.getSalario()) + (3000 * Departamentos.length);
    }
    
}

