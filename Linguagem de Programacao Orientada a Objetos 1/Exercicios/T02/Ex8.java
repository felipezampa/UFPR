// Bruna da Silva Lins
// Felipe Augusto Vieira Zampa
// Vithor da Silva Modanese

import java.util.Scanner;

public class Ex8 {
   public static void main (String args []){
      int x1 = 0;
      int y1 = 0;
      int x2 = 0;
      int y2 = 0;
      double d;
       Scanner scn = new Scanner(System.in);
       System.out.println("Digite o x1: ");
       x1 = scn.nextInt();
        System.out.println("Digite o y1: ");
       y1 = scn.nextInt();
        System.out.println("Digite o x2: ");
       x2 = scn.nextInt();
        System.out.println("Digite o y2: ");
       y2 = scn.nextInt();
       d = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
       System.out.println("Distancia: "+d);
   }

}