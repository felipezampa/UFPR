/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * @ Lista 4 exercicio 2
 */
package T04_Ex2;
import T04_Ex2.Pessoa;

public class Ex2 {
        
    public static void main(String args[]){
        //Criação dos objetos
        Pessoa Joao = new Pessoa();
        Pessoa Maria = new Pessoa();

        Joao.criaNome("Joao");
        Joao.criaIdade(20);
        Joao.criaEndereco("Rua Principal, 77");
        Joao.fazAniversario(1);
        Joao.imprime();

        Maria.criaNome("Maria");
        Maria.criaIdade(25);
        Maria.criaEndereco("Rua Brasil, 150");         
        Maria.fazAniversario(1);
        Maria.imprime();
    } 
    
}
