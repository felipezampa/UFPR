/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 4 exercicio 3
 */
package T04_Ex3;

public class UsaPonto3D {
    
    public static void main(String args[]){
        
        double distancia;
        Ponto3D a = new Ponto3D();
        
        a.setX(1);
        a.setY(2);
        a.setZ(3);
        
        distancia = a.calculaDistancia(3,4,5);
        
        System.out.println("A distancia é:   " + distancia);
    
    } 
}
