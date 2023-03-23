/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public class UsaFormas {
    
    public static void main (String args[]){
        double soma;
        
        Retangulo r = new Retangulo(2, 4);
        Triangulo t = new Triangulo (2, 3, 5);
        Circunferencia c = new Circunferencia(2);
        
        Forma[] listaFormas = {r, t, c};
        
        soma = SomaAreas.somaArea(listaFormas);
        
        System.out.printf("\nA soma das áreas das formas é: %.2f", soma);
    }
    
        public static double somaAreaFormas(Forma[] listaFormas) {
        double soma = 0.0;
        
        for(int i = 0; i < listaFormas.length; i++) {
            soma += listaFormas[i].area();
        }
        
        return soma;
    }
}
