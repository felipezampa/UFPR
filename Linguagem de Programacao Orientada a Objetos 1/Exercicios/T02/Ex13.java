// Felipe Augusto Vieira Zampa
// Bruna da Silva Lins
// Vithor da Silva Modanese

import java.util.Scanner;

public class Ex13{  

	public static void main(String args[]) {  
	
		int maximo;
		int num1 = 0;
		int num2 = 1;
		int num3 = 0;
		Scanner entrada = new Scanner(System.in);
		
		try{

			System.out.println("Digite o número:");
			maximo = entrada.nextInt();
			
			if ( maximo <= 0 ){
				System.out.println("Número errado!!");
				
			} 
			else{
				
				System.out.print(num1 + " " + num2);
				
				while ( num3 <= maximo ){
					
					num3 = num1 + num2;
					
					System.out.print(" " + num3);
					
					num1 = num2;
					num2 = num3;
					
				}
		
				
			}
		} 
		catch(Exception e){
			
			System.out.println("Dado errado, por favor tente novamente!!");
			
		}
		
	 }    
  
} 