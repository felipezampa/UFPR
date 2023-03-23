/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 3
 */
package T07_Ex3;

import java.util.Scanner;

public class UsaRetangulo {
    
    public static void main(String[] args) {
        
        double largura,altura;
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Digite a largura do retangulo: ");
            largura = sc.nextDouble();
            System.out.println("Digite a altura do retangulo: ");
            altura = sc.nextDouble();
            Retangulo r = new Retangulo(altura,largura);
            
        } 
        catch(IllegalArgumentException e) {
            System.out.println("\nExceção: " + e);
        }
    }
}
