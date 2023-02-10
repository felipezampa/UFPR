/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.Scanner;

public class Ex11 {
   public static void main(String[] args) {
      double matriz[][] = new double [3][3];
      double DP1, DP2, DP3, DS1, DS2, DS3; 
      double det = 0;
      Scanner s = new Scanner(System.in);
      for (int i=0; i<matriz.length; i++) {
         for (int c=0; c<matriz[i].length; c++) {
            
            System.out.printf("informe o %d° valor da linha %d : ", c+1, i+1);
            matriz[i][c] = s.nextDouble();
         }
      }
      s.close();
      DP1 = matriz[0][0] * matriz[1][1] * matriz[2][2];
      DP2 = matriz[0][1] * matriz[1][2] * matriz[2][0];
      DP3 = matriz[0][2] * matriz[1][0] * matriz[2][1];
      
      DS1 = matriz[2][0] * matriz[1][1] * matriz[0][2];
      DS2 = matriz[2][1] * matriz[1][2] * matriz[0][0];
      DS3 = matriz[2][2] * matriz[1][0] * matriz[0][1];
      
      det = -(DS1 + DS2 + DS3) + (DP1 + DP2 + DP3);
      for (int i = 0; i < matriz.length; i++){
            for (int c = 0; c < matriz[i].length; c++)
            {
            System.out.print (matriz[i][c] + "\t");
            }
            System.out.println ();
         }
      System.out.println("determinante: " + det);
      
   }
}