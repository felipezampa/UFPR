// Felipe Augusto Vieira Zampa
// Bruna da Silva Lins
// Vithor da Silva Modanese

import java.util.Scanner;

public class Ex9{
	
	public static void main (String args[]){
				
		double a = 0;
		double b = 0;
		double c = 0;
		double x1 = 0;
		double x2 = 0;
		double delta;	
		Scanner entrada = new Scanner(System.in);
		
			System.out.println ("Digite o valor de a:");
			a = entrada.nextDouble();
			
			System.out.println ("Digite o valor de b:");
			b = entrada.nextDouble();
			
			System.out.println ("Digite o valor de c:");
			c = entrada.nextDouble();
			
			
			delta = (b * b) - ( 4 * a * c);		
			
			
			if( delta > 0 ){
				x1 = ( - b + Math.sqrt(delta) ) / ( 2 * a );
	    		x2 = ( - b - Math.sqrt(delta) ) / ( 2 * a );
				System.out.println("x1= " + x1 + "  x2= " + x2);
			} 
			
			else if( delta == 0 ){
				x1 = ( - b + Math.sqrt(delta) ) / ( 2 * a );
				x2 = x1;
				System.out.println("x1= " + x1 + "  x2= " + x2);
			}
			
			else{
				System.out.println("Não existe raiz");
			}	
						
	}
}
