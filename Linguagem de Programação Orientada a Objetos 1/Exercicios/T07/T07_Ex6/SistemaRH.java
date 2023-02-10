/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 6
 */
package T07_Ex6;

public class SistemaRH {
    
    public static void main(String[] args) {
        
        String[] Departamentos = {"Departamento de Análise"};
        String[] Funcionarios = {"João","Maria","Julia"};
        
        Funcionario[] listaFuncionarios = new Funcionario[3];
        
        try {
            Diretor diretor = new Diretor("Felipe Zampa", "Rua Curitiba", 20, 311990140, 21000, Departamentos);
            Gerente gerente = new Gerente("Bruna Lins", "Rua Brasil", 31, 232057080, 9000, Funcionarios);
            Analista analista = new Analista("Rafael Silva", "Rua São Paulo", 40, 445680470, 5000);

            listaFuncionarios[0] = diretor;
            listaFuncionarios[1] = gerente;
            listaFuncionarios[2] = analista;
            Imprime(listaFuncionarios);
        } 
        catch(DiretorException | GerenteException exDir) {
            System.out.println("Exceção: \n" + exDir);
        }
    }
    
    public static void Imprime(Funcionario[] listaFuncionarios) {
        for(int i = 0; i < listaFuncionarios.length; i++) {
            System.out.printf("\nFuncionário %d: %s", i + 1, listaFuncionarios[i].getNome());
            System.out.printf("\nBônus do funcionário %d: R$%.2f", i + 1, listaFuncionarios[i].getBonus());
            
            System.out.println("");
        }
    }
}