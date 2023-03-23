/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 2
 */
package T07_Ex2;

public class MarchaException extends Exception {
    public MarchaException() {
        super("+++ERRO no programa!+++\nMarcha deve ser positivo entre 0 e 24!");
    }
}
