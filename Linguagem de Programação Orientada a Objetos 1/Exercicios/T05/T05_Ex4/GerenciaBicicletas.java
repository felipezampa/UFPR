/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 5 exercicio 4
 */
package T05_Ex4;

public class GerenciaBicicletas {
    
    public static void main(String[] args) {
        
        Bicicleta bicicletaDaMaria = new Bicicleta();
        
        bicicletaDaMaria.mudarCadencia(50);
        bicicletaDaMaria.aumentarVelocidade(10);
        bicicletaDaMaria.mudarMarcha(20);
        bicicletaDaMaria.printStates();
    }

}
