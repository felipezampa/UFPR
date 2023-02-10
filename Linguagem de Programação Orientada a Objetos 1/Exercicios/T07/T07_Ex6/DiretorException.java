/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 6
 */
package T07_Ex6;

public class DiretorException extends Exception{
    
    public DiretorException() {
        super("\n+++ERRO+++\nO Diretor deve ter pelo menos 2 departamentos!");
    }
    
}
