/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 6
 */
package T07_Ex6;

public class Diretor extends Funcionario {
    
    private String[] Departamentos;
    
    public Diretor(String nome, String endereco, int idade, int cpf, double salario, String[] Departamentos) throws DiretorException {
        super(nome, endereco, idade, cpf, salario);
        
        if(Departamentos.length >= 2) {
            this.Departamentos = Departamentos;
        } else {
            throw new DiretorException();
        }
        
    }
    
    public double getBonus() {
        return (4 * super.getSalario()) + (3000 * Departamentos.length);
    }
}