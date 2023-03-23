/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 5 exercicio 8
 */
package T05_Ex8;


import java.util.Scanner;

public class main{
    
    public static void main(String args[]) {
        int escolha;
        Scanner s = new Scanner(System.in);
        do {
            System.out.printf("Digite a opção desejada:\n 1 - Celsius para farenheit\n 2 - Celsius para Kelvin\n" +
                    " 3 - Farenheit para celsius\n 4 - Farenheit para kelvin\n " +
                    "5 - Kelvin para farenheit\n 6 - Kelvin para celsius\n 7 - Sair\n");
            escolha = s.nextInt();

            switch (escolha) {
                case 1:
                    System.out.printf("digite o valor em Celsius a ser convertido.\n");
                    double a = s.nextDouble();
                    System.out.println(celsiusFK.celsiusFarenheit(a));
                    break;
                case 2:
                    System.out.printf("digite o valor em Celsius a ser convertido.\n");
                    double b = s.nextDouble();
                    System.out.println(celsiusFK.celsiusKelvin(b));
                    break;
                case 3:
                    System.out.printf("digite o valor em Farenheit a ser convertido.\n");
                    double c = s.nextDouble();
                    System.out.println(celsiusFK.farenheitCelsius(c));
                    break;
                case 4:
                    System.out.printf("digite o valor em Farenheit a ser convertido.\n");
                    double d = s.nextDouble();
                    System.out.println(celsiusFK.farenheitKelvin(d));
                    break;
                case 5:
                    System.out.printf("digite o valor em Kelvin a ser convertido.\n");
                    double e = s.nextDouble();
                    System.out.println(celsiusFK.kelvinFarenheit(e));
                    break;
                case 6:
                    System.out.printf("digite o valor em Kelvin a ser convertido.\n");
                    double f = s.nextDouble();
                    System.out.println(celsiusFK.kelvinCelsius(f));
                    break;
                case 7:
                    System.out.printf("bye bye\n");
                    break;
                default:
                    System.out.printf("erro");
                    break;

            }
        } while (escolha < 7);
    }
}