/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public class SomaAreas {
    
    public static double somaArea(Forma[] listaFormas) {
        
        double soma = 0.0;
        
        for(int i = 0; i < listaFormas.length; i++) {
            soma += listaFormas[i].area();
        }
        
        return soma;
        
    }
}
