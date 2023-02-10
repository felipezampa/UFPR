/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.*;

public class Ex2 {
	
    public static void main(String args[]){

		double media = 0.0;
		double aux = 0.0;
		double valorNum ;
		String valor = "";
		int contagemNum = 0;
		Scanner sc = new Scanner(System.in);

		while(true){
			
			try{
			
				System.out.println("Digite um numero: ");
				valor = sc.nextLine();
				
				if(valor.equals("S") || valor.equals("s")){
					break;
				}
				else{
					contagemNum++;
					valorNum = Double.parseDouble(valor);
				
					//verifica a média
					aux = ( aux + valorNum);
					media = ( aux / contagemNum );				
					
				}
			}
			catch (Exception e){
					System.out.println("Erro, digite novamente um valor válido.");
			}
					
		}
		sc.close();
		System.out.printf("\nA média dos números:  "+ media+"\n\n");
		
	}
}