/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.*;

public class Ex3 {

  public static void main(String args[]) {

    String numsEscolhidos = "";
    int i = 0;
    int j = 0;
    Scanner s = new Scanner(System.in);

    for (i = 0; i < 20; i++) {
      try {
        int n = (i + 1);
        System.out.println("digite o valor número " + n + ":");
        numsEscolhidos = s.next();

        if (Integer.parseInt(numsEscolhidos) % 2 == 0) {
          j = j + Integer.parseInt(numsEscolhidos);
        } else {
          System.out.println("Impar!");
        }

      } catch (Exception e) {
        i++;
        System.out.println("Voce digitou um caracter invalido!!");
      }
    }

    s.close();
    System.out.println("a soma de todos os números pares é: " + j);

  }

}