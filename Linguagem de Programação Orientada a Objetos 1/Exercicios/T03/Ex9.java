/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.Scanner;

public class Ex9 {

    public static String limpaPalavra (String palavraAntes){
        
        String palavraLimpa;
        palavraLimpa = palavraAntes.toLowerCase();
        palavraLimpa = palavraLimpa.replaceAll(" ", "");
        palavraLimpa = palavraLimpa.replaceAll("\\p{Punct}", "");

        return palavraLimpa;
    }

    public static boolean conferePalindromo(String palavra) {
        // coloca o a como 0 para pegar a primeira posição
        int a = 0;
        // e o b pra pegar a última posição e tem que subtrair 1
        // pois a funcao length começa da posição 1 e não da 0
        int b = palavra.length() - 1;

        while (a < b) {

            if (palavra.charAt(a) != palavra.charAt(b)) {
                return false;
            }
            a++;
            b--;
        }
        return true;
    }

    public static void main(String[] args) {

        String palavra;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a string:  ");
        palavra = sc.nextLine();

        sc.close();
        System.out.println(palavra);
        palavra = limpaPalavra(palavra);

        if (conferePalindromo(palavra) == true) {
            System.out.printf("\nA palavra é um palíndromo");
        } else {
            System.out.printf("\nNão é um palíndromo");
        }
    }
}