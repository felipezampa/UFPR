/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 4 exercicio 9
 */
package T04_Ex9;

public class UsaCircunferencia {
    
    public static void main(String args[]){
    
        Circunferencia a = new Circunferencia();
        double raio;
        double area;
        
        a.setRaio(7);
        raio = a.getRaio();
        area = a.calculaArea();
        
        System.out.println("O raio é:   " + raio);
        System.out.println("A área é: " + area);
    
    } 
}
