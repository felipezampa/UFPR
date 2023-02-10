/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.Scanner;

public class Ex7 {

    public static void main(String args[]) {

        String[] nome;
        double[] salario;
        int quant;
        double media;
        double aux = 0.0;

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a quantidade de funcionarios da empresa: ");
            quant = sc.nextInt();

            nome = new String[quant];
            salario = new double[quant];

            for (int i = 0; i < quant; i++) {
                int n = i + 1;

                System.out.printf("Digite o nome do %do funcionario: ", n);
                nome[i] = sc.next();

                System.out.print("E o seu salario: ");
                salario[i] = sc.nextDouble();

                if (salario[i] <= 0) {
                    System.out.println("Salario Invalido, digite novamente: ");
                    i--;
                } else {
                    aux += salario[i];
                }

            }

            media = (aux / quant);
            System.out.printf("\nMedia salarial: R$%5.2f%n", media);
            System.out.printf("\n\nFuncionarios que ganham acima da media empresarial:\n");

            for (int i = 0; i < quant; i++) {

                if (salario[i] > media) {
                    System.out.printf("Nome: %10s  | Salario: R$ %9.2f%n", nome[i], salario[i]);
                } else {
                }
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Erro, digite novamente um valor válido.");
        }

    }
}