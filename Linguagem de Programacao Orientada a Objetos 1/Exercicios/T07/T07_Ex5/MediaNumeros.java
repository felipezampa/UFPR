/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 5
 */
package T07_Ex5;

import java.util.*;

public class MediaNumeros {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String numeroDigitado;
        float soma = 0, media = 0;
        int i = 0;
        System.out.println("Informe os números na sequencia solicitada.");
        System.out.println("Para sair digite 'S'");
        
        while (true) {
            System.out.print("\nNumero " + (i+1) + ":   ");
            numeroDigitado = sc.next();

            if (numeroDigitado.equalsIgnoreCase("s")) {
                break;
            }
                
            try {
                if (testaInteiro(numeroDigitado)) {
                    int numero = Integer.parseInt(numeroDigitado);
                    
                    soma += numero;
                    i++;
                } else {
                    throw new InputMismatchException();
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro de entrada.\n" 
                                    + numeroDigitado + 
                                    " não é um numérico válido. Tente novamente ou digite S para sair.\n");
            } finally {
                clearBuffer(sc);	
            }
        }
        
        media = ( soma / i );
        
        System.out.printf("\nA soma dos números digitados é:  %7.2f\n", soma);
        System.out.printf("A média dos números digitados é: %7.2f\n", media);
    }
        
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    
    public static boolean testaInteiro(String str) {
        boolean ehInteiro = true;
        try {
          int i = Integer.parseInt(str);
        } catch(NumberFormatException nfe) {
           ehInteiro = false;
        }
        return ehInteiro;
    }
  
}
