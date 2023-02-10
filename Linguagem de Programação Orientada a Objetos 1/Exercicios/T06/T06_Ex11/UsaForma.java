/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 11
 */
package T06_Ex11;

public class UsaForma {
    
    public static void main(String[] args) {
        Superficie cir = new Circunferencia(2);
        System.out.println(cir.toString());
        System.out.println("Area: " + cir.area());
        
        Superficie qua = new Quadrado(2, 4);
        System.out.println(qua.toString());
        System.out.println ("Area: " + qua.area());
    }
    
}
