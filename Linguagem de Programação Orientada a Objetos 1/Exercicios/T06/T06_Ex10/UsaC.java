/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 10
 */
package T06_Ex10;

public class UsaC {
    
    public static void main (String args[]){
        System.out.println("Iniciando o programa...");
        System.out.println("Instanciando C1_2 sem parametros...");
        C1_2 objC1 = new C1_2();
        
        System.out.println("Instanciando C1_2 com parametros...");
        C1_2 objC2 = new C1_2("XPTO", 3.141516f);
        
        System.out.printf("\nChamando operacoes na instancia 1:");
        objC1.operacao1();
        objC1.operacao2();
        
        System.out.printf("\nChamando operacoes na instancia 2:");
        objC2.operacao1();
        objC2.operacao2();
        
        System.out.printf("\nInstanciando objeto em referencia para interface...");
        I1 objC3 = new C1_2("YSBC", 1.99f);;
        
        System.out.printf("\nChamando operacoes na referencia para interface:");
        objC3.operacao1();
    }
}
