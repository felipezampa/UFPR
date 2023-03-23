/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 2
 */
package T07_Ex2;

import java.util.Scanner;

public class UsaBicicleta {
    
    public static void main(String[] args) {
        
        int RPM;
        int velocidade;
        int marcha;
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Digite o RPM: ");
            RPM = sc.nextInt();
            System.out.println("Digite a velocidade: ");
            velocidade = sc.nextInt();
            System.out.println("Digite a marcha: ");
            marcha = sc.nextInt();
            
            Bicicleta bicicleta = new Bicicleta(RPM, velocidade, marcha);
            
        } catch(RpmException | VelocidadeException | MarchaException ex) {
            System.out.println("\nExceção: \n" + ex);
        }
    }
    
}
