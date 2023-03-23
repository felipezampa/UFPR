/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 3
 */
package T06_Ex3;

public class SistemaRH {
      
    public static void main(String[] args) {
        
        String[] Departamentos = {"Departamento de Desenvolvimento","Departamento de Análise","Departamento de Dados"};
        String[] Funcionarios = {"Felipe","Bruna","Joao","Pedro","Maria","Julia"};
        
        Funcionario[] listaFuncionarios = new Funcionario[3];
        Diretor diretor = new Diretor("Felipe Zampa", "Vila Mariana", 54, 147896145, 12000, Departamentos);
        Gerente gerente = new Gerente("Bruna Lins", "Rua da União", 47, 78966893, 8000, Funcionarios);
        Analista analista = new Analista("Rafael Wandresen", "Rua Vila Isabel", 24, 654563212, 5000);
        
        listaFuncionarios[0] = diretor;
        listaFuncionarios[1] = gerente;
        listaFuncionarios[2] = analista;
        
        imprimeRelatorio(listaFuncionarios);
        
    }
    
    public static void imprimeRelatorio(Funcionario[] listaFuncionarios) {
        
        for(int i = 0; i < listaFuncionarios.length; i++) {
            System.out.printf("\nFuncionário %d: %s", i + 1, listaFuncionarios[i].getNome());
            System.out.printf("\nBônus do funcionário %d: R$ %.2f\n", i + 1, listaFuncionarios[i].getBonus());
        }
        
    }
}
