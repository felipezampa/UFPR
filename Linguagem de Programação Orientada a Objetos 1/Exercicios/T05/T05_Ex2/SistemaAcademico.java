/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 *  Lista 5 exercicio 2
 */
package T05_Ex2;

import java.util.*;

public class SistemaAcademico {
    private static Aluno alunos[];
    private static int quantidade = 0;
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        SistemaAcademico.menu();
    }
    
    public static void menu() {
        String nomeAluno, nomeDisciplina, informe;
        int quantidadeTotalAlunos, i;
        boolean condicao = true;
        
        System.out.println("Informe a quantidade de alunos que serão cadastrados: ");
        quantidadeTotalAlunos = sc.nextInt();
        
        alunos = new Aluno[quantidadeTotalAlunos];
        
        while (condicao == true) {
            System.out.println("\n==========================MENU===========================");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Excluir aluno por nome");
            System.out.println("3 - Listar Alunos");
            System.out.println("4 - Matricular um Aluno em uma Disciplina");
            System.out.println("5 - Cancelar Matrícula");
            System.out.println("6 - Imprimir lista de Alunos e suas Disciplinas Matriculadas");
            System.out.println("7 - Sair");
            System.out.println("=========================================================");
            System.out.println("\nOpção: ");
            int entrada = sc.nextInt();
            clearBuffer(sc);
            
            switch (entrada) {
                case 1: ;
                    cadastrarAluno(alunos[quantidade]);
                    
                    break;
                case 2:
                    System.out.println("\nDigite o nome do aluno a ser excluído: ");
                    nomeAluno = sc.nextLine();
                    
                    excluirAlunoPorNome(nomeAluno);
                    
                    break;
                case 3: ;
                    listarAlunos();

                    break;
                case 4: ;
                    System.out.println("\nDigite o nome do aluno a ser matriculado em uma disciplina: ");
                    nomeAluno = sc.nextLine();
                    
                    System.out.println("\nDigite o nome da disciplina a qual o aluno será matriculado: ");
                    nomeDisciplina = sc.nextLine();
                    
                    i = 0;
                    while(i < quantidade && !nomeAluno.equals(alunos[i].getNome())) {
                        i++;
                    }
                    
                    if (i < quantidade) {
                        informe = matricularAlunoEmDisciplina(alunos[i], nomeDisciplina);
                        System.out.printf("\n%s", informe);
                    } else {
                        System.out.println("\nAluno não encontrado.");
                    }
                    
                    break;
                case 5: ;
                    System.out.println("\nDigite o nome do aluno que terá sua disciplina cancelada: ");
                    nomeAluno = sc.nextLine();
                    
                    System.out.println("\nDigite o nome da disciplina que será cancelada: ");
                    nomeDisciplina = sc.nextLine();
                    
                    i = 0;
                    while(i < quantidade && !nomeAluno.equals(alunos[i].getNome())) {
                        i++;
                    }
                    
                    if (i < quantidade) {
                        informe = cancelarMatricula(alunos[i], nomeDisciplina);
                        System.out.printf("\n%s", informe);
                    } else {
                        System.out.println("\nAluno não encontrado.");
                    }

                    break;
                case 6: ;
                    imprimirListaDeAlunoseDisciplinas();

                    break;
                case 7: ;
                    condicao = false;

                    break;
                default:
                    System.out.println("###ERRO!Opção de menu invalida.");
            }
        }
    }
    
    public static void cadastrarAluno(Aluno aluno){
        Scanner sc = new Scanner(System.in);
        
        String nome, endereco, matricula, curso;
        int idade, periodo, quantidadeDisciplinasPermitidas = 0;
        
        System.out.println("\nDigite o nome do aluno: ");
        nome = sc.nextLine();
        
        System.out.println("Digite a idade do aluno: ");
        idade = sc.nextInt();
        clearBuffer(sc);
        
        System.out.println("Digite o endereço do aluno: ");
        endereco = sc.nextLine();
        
        System.out.println("Digite a matrícula do aluno: ");
        matricula = sc.nextLine();
        
        System.out.println("Digite o curso do aluno: ");
        curso = sc.nextLine();
        
        System.out.println("Digite o período do aluno: ");
        periodo = sc.nextInt();
        clearBuffer(sc);
        
        aluno = new Aluno(nome, idade, endereco, matricula, curso, periodo, quantidadeDisciplinasPermitidas);
        
        alunos[quantidade] = aluno;
        quantidade++;
    }
    
    public static void excluirAlunoPorNome(String nomeAluno) {
        int i = 0;
        while(i < quantidade && !nomeAluno.equals(alunos[i].getNome())) {
            i++;
        }
        
        if (i < quantidade) {
            for (int k = i; k < quantidade; k++) {
                alunos[k] = alunos[k+1];
            }
            
            quantidade--;
        }
    }
    
    public static Aluno[] listarAlunos() {
        System.out.println("");
        
        for (int i = 0; i < quantidade; i++) {
            System.out.printf("\nAluno[%d]: %s", i + 1, alunos[i].getNome());
        }
        
        System.out.println("");
        
        return alunos;
    }
    
    public static String matricularAlunoEmDisciplina(Aluno aluno, String disciplina) {
        if (aluno.getquantidadeDisciplinasMatriculadas() == 0) {
            System.out.println("\nDigite quantas disciplinas o aluno deve ser matriculado: ");
            int quantidadeDisciplinas = sc.nextInt();
            clearBuffer(sc);
            
            aluno.setQuantidadeDisciplinasPermitidas(quantidadeDisciplinas);
        }
        
        return aluno.fazMatricula(disciplina);
    }
    
    public static String cancelarMatricula(Aluno aluno, String disciplina) {
        return aluno.cancelarMatricula(disciplina);
    }

    public static String imprimirListaDeAlunoseDisciplinas () {
        String aluno = "";
        for (int i = 0; i < quantidade; i++) {
            aluno = alunos[i].imprime();
            System.out.printf("%s", aluno);
        }
        
        return "";
    }
    
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}

