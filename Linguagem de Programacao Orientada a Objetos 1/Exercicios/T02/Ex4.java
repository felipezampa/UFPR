// Felipe Augusto Vieira Zampa
// Bruna da Silva Lins
// Vithor da Silva Modanese
// 4. (Tarefa 2) Faça uma classe executável que dados a distância percorrida (em Km)e o 
// tempo gasto em uma viagem (em horas), informe a velocidade média do carro, sabendo 
// que Velocidade = S / T (variação de distância / variação do tempo). Imprima o valor com 
// duas casas decimais. 

import java.util.Scanner;

public class Ex4{
	
	public static void main (String args[]){
				
		float distancia = 0;
		float tempo = 0;
		float velocidade =0;
		Scanner scan = new Scanner(System.in);
		
			System.out.println ("Digite a distancia percorrida: ");
			distancia = scan.nextFloat();
			
			System.out.println ("Digite o tempo gasto: ");
			tempo = scan.nextFloat();
			
			velocidade = (distancia / tempo);			
			
			System.out.printf("A velocidade média foi= %.2f", velocidade);
	}
}
