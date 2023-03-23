/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * @ Lista 4 exercicio 2
 */
package T04_Ex2;
public class Pessoa {

        //Passagem dos atributos para a classe
        static String nome;
        static int idade = 0;
        static String endereco;
        
        public static void criaNome(String novoNome){
           nome = novoNome;
        }
        
        public static void criaIdade(int novaIdade){
           idade = novaIdade;
        }
        
        public static void criaEndereco(String novoEndereco){
           endereco = novoEndereco;
        }
       
        public static void fazAniversario(int incremento){
            idade += incremento;
        }
        
        public void imprime(){
            System.out.println("Nome:     " + nome);
            System.out.println("Idade:    " + idade);
            System.out.println("Endereco: " + endereco);
            System.out.println();
        }  
 
}