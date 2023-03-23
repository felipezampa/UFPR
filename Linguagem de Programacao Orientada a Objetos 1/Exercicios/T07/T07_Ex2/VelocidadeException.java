/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 2
 */
package T07_Ex2;

public class VelocidadeException extends Exception {
    public VelocidadeException() {
        super("+++ERRO+++\nVelocidade deve ser positivo e não pode ultrapassar 100km/h!"); 
    }
}
