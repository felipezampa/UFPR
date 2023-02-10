/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 6
 */
package T07_Ex6;

public class GerenteException extends Exception{
    
    public GerenteException() {
        super("\n+++ERRO+++\nO Gerente deve gerenciar pelo menos 5 funcionários!");
    }
    
}
