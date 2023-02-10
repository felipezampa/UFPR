/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        int matriz[][];
        Scanner scn = new Scanner(System.in);
        int linha, coluna = 0;
        System.out.println("Informe a quantidade de linhas da matriz");
        linha = scn.nextInt();
        System.out.println("Informe a quantidade de colunas da matriz");
        coluna = scn.nextInt();
        matriz = new int[linha][coluna];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                System.out.println("matriz [" + i + "][" + j + "] =");
                matriz[i][j] = scn.nextInt();
            }
        }
        scn.close();

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }

        int diagonalPrincipal = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i == j) {
                    diagonalPrincipal += matriz[i][j];
                }
            }
        }

        System.out.println("\n\nDiagonal Principal = " + diagonalPrincipal);
        int diagonalSecundaria = 0;

        for(int i = matriz.length - 1; i >= 0; i--) {
            for(int j = matriz.length - 1; j >= 0; j--) {
                if(i + j == matriz.length - 1) {
                    diagonalSecundaria += matriz[i][j];
                }
            }
        }
        
        System.out.println("Diagonal Secundária = " + diagonalSecundaria);
        int soma = diagonalPrincipal + diagonalSecundaria;
        System.out.println("Soma das diagonais (P) + (S) = " + soma);
    }
}