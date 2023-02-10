/** 
@author Felipe Augusto Vieira Zampa
@author Bruna da Silva Lins
*/

import java.util.Scanner;

public class Ex13{
    
    public static void main(String args[]){
    
        int i = 0;
        double soma = 0.0; 
        double desvioPadrao = 0.0;
        Scanner s = new Scanner( System.in);
        int tamanho = 0;

        System.out.printf("informe a quantidade de números: ", tamanho);
        tamanho = Integer.parseInt( s.nextLine() );
        double conjunto[] = new double [tamanho];
        int tam = conjunto.length;
        
        for (i = 0; i<tam; i++){
            
            int n = i + 1;
            System.out.printf("informe o valor na posição %d: ", n);
            conjunto[i] = s.nextDouble();

        }
        

        for(i = 0; i < tam; i++){
            soma = soma + conjunto[i];
        }
        
        double media = soma / tam;
        
        for(i = 0; i < tam; i++){
            desvioPadrao = desvioPadrao + Math.pow(conjunto[i] - media, 2);
        }
        
        System.out.println("Desvio padrão amostral: " + Math.sqrt(desvioPadrao / tam));
    
    }
    
    
}