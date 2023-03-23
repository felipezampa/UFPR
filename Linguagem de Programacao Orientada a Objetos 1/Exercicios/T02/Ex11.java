// Felipe Augusto Vieira Zampa
// Bruna da Silva Lins
// Vithor da Silva Modanese

import java.util.Scanner;

public class Ex11{
	
	public static void main (String args[]){
		
		String cargo;
		String salarioInicio;
		float salario;
		double bonus = 0;
		Scanner entrada = new Scanner(System.in);
		int departamentos, pessoa;
		
		System.out.println("Por favor digite seu cargo: ");
		cargo = entrada.nextLine();
		
		System.out.println("Agora digite seu salario atual: ");
		salarioInicio = entrada.nextLine();
			
		salario = Float.parseFloat(salarioInicio);  	

			switch(cargo){
				case "Diretor":
					System.out.println("Quantos departamentos você gerencia?");
					departamentos = entrada.nextInt();
					bonus = (salario * 4) + (departamentos * 3000.00);
				break;
				case "Gerente":
					System.out.println("Quantas pessoas você gerencia?");
					pessoa = entrada.nextInt();
					bonus = (salario * 2) + (pessoa * 100.00);
				break;
				case "Analista":
					bonus = (salario * 1);	
				break;
				case "Programador":
					bonus = (salario * 0.8);	
				break;
				case "Auxiliar de Limpeza":
					bonus = (salario * 0.5);	
				break;
			}
			
		System.out.println("Seu bonus anual será de: R$" + bonus);
		
				
		
	}
}
