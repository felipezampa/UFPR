/** Classe com um único metodo

@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
@author Vithor da Silva Modanese

*/

import java.util.Scanner;

public class Ex5 {
    public static void main(String args[]) {
      int quantidade;
      double multa;
      float preco;
      float faturamento;
      double faturamentoMultas;
      Scanner entrada = new Scanner(System.in);
      
      /**le entrada de quantidade dos dvds */  
        System.out.println("Digite a quantidade de DVDs = ");
        quantidade = entrada.nextInt();
      
      /**le entrada do preco dos dvds */ 
        System.out.println("Digite o preço de aluguel dos DVDs = ");
        preco = entrada.nextFloat();
      
      /**multiplica quantidade e preco e divide por 3 para ter os ganhos mensais, depois multiplica por 12 meses */ 
        faturamento = ((quantidade * preco) / 3) * 12;
      /**Utiliza o printf que nem no C para conseguir usar os comandos de casas após a virgula e \n para pular linha */ 
        System.out.printf("\nO faturamento anual é de R$%.2f\n",faturamento); 
      
      /**calcula 10% do preco para ver o preco da multa */ 
         multa = (preco * 0.1);
      /**calcula 10% da multa para ver os ganhos mensais */ 
         faturamentoMultas = (multa * 0.1); 
      
      //utiliza novamente o printf */ 
         System.out.printf("\nO valor ganho com multas por mês é de R$%.2f\n",multa);
    }
}