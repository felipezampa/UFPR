/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 4
 */
package T06_Ex4;

import java.util.*;

public class SistemaAcademico {

    private static Professor professores[];
    private static int quantidadeProfessor = 0;
    private static Aluno alunos[];
    private static int quantidadeAluno = 0;
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        SistemaAcademico.menu();
    }
    
    public static void menu() {
        String nomeProfessor, nomeAluno, nomeDisciplina, informe;
        Disciplina disciplina;
        int quantidadeTotalProfessores, quantidadeTotalAlunos, i;
        boolean condicao = true;
        
        System.out.println("Informe a quantidade de professores que serão cadastrados: ");
        quantidadeTotalProfessores = sc.nextInt();
        
        professores = new Professor[quantidadeTotalProfessores];
        
        System.out.println("\nAgora, informe a quantidade de alunos que serão cadastrados: ");
        quantidadeTotalAlunos = sc.nextInt();
        
        alunos = new Aluno[quantidadeTotalAlunos];
        
        while (condicao == true) {
            System.out.println("\n==========================MENU===========================");
            System.out.println("1) Cadastrar Professor");
            System.out.println("2) Cadastrar Aluno");
            System.out.println("3) Excluir aluno por nome");
            System.out.println("4) Listar Alunos");
            System.out.println("5) Relacionar um Professor a uma Disciplina");
            System.out.println("6) Matricular um Aluno em uma Disciplina");
            System.out.println("7) Cancelar Matrícula");
            System.out.println("8) Imprimir lista de Alunos e suas Disciplinas Matriculadas");
            System.out.println("9) Sair");
            System.out.println("===========================================================");
            System.out.println("\nOpção: ");
            int entrada = sc.nextInt();
            clearBuffer(sc);
            
            switch (entrada) {
                case 1: ;
                    cadastrarProfessor(professores[quantidadeProfessor]);
                    
                    break;
                case 2: ;
                    cadastrarAluno(alunos[quantidadeAluno]);
                    
                    break;
                case 3:
                    System.out.println("\nDigite o nome do aluno a ser excluído: ");
                    nomeAluno = sc.nextLine();
                    
                    excluirAlunoPorNome(nomeAluno);
                    
                    break;
                case 4: ;
                    listarAlunos();

                    break;
                case 5: ;
                    System.out.println("\nDigite o nome do professor a ser relacionado a uma disciplina: ");
                    nomeProfessor = sc.nextLine();
                    
                    System.out.println("\nDigite o nome da disciplina a qual o professor será relacionado: ");
                    nomeDisciplina = sc.nextLine();
                    
                    disciplina = new Disciplina(nomeDisciplina);
                    
                    i = 0;
                    while(i < quantidadeProfessor && !nomeProfessor.equals(professores[i].getNome())) {
                        i++;
                    }
                    
                    if (i < quantidadeProfessor) {
                        informe = relacionarProfessorADisciplina(professores[i], disciplina);
                        System.out.printf("\n%s", informe);
                    } else {
                        System.out.println("\nProfessor não encontrado.");
                    }
                    
                    break;
                case 6: ;
                    System.out.println("\nDigite o nome do aluno a ser matriculado em uma disciplina: ");
                    nomeAluno = sc.nextLine();
                    
                    System.out.println("\nDigite o nome da disciplina a qual o aluno será matriculado: ");
                    nomeDisciplina = sc.nextLine();
                    
                    disciplina = new Disciplina(nomeDisciplina);
                    
                    i = 0;
                    while(i < quantidadeAluno && !nomeAluno.equals(alunos[i].getNome())) {
                        i++;
                    }
                    
                    if (i < quantidadeAluno) {
                        informe = matricularAlunoEmDisciplina(alunos[i], disciplina);
                        System.out.printf("\n%s", informe);
                    } else {
                        System.out.println("\nAluno não encontrado.");
                    }
                    
                    break;
                case 7: ;
                    System.out.println("\nDigite o nome do aluno que terá sua disciplina cancelada: ");
                    nomeAluno = sc.nextLine();
                    
                    System.out.println("\nDigite o nome da disciplina que será cancelada: ");
                    nomeDisciplina = sc.nextLine();
                    
                    i = 0;
                    while(i < quantidadeAluno && !nomeAluno.equals(alunos[i].getNome())) {
                        i++;
                    }
                    
                    if (i < quantidadeAluno) {
                        informe = cancelarMatricula(alunos[i], nomeDisciplina);
                        System.out.printf("\n%s", informe);
                    } else {
                        System.out.println("\nAluno não encontrado.");
                    }

                    break;
                case 8: ;
                    imprimirListaDeAlunoseDisciplinas();

                    break;
                case 9: ;
                    condicao = false;

                    break;
                default:
                    System.out.println("Erro, por favor digite uma opção válida");
            }
        }
    }
    
    public static void cadastrarProfessor(Professor professor){
        String nome, endereco;
        int idade, quantidadeDisciplinasMinistradas = 0;
        
        System.out.println("\nDigite o nome do professor: ");
        nome = sc.nextLine();
        
        System.out.println("Digite a idade do professor: ");
        idade = sc.nextInt();
        clearBuffer(sc);
        
        System.out.println("Digite o endereço do professor: ");
        endereco = sc.nextLine();
        
        professor = new Professor(nome, idade, endereco, quantidadeDisciplinasMinistradas);
        
        professores[quantidadeProfessor] = professor;
        quantidadeProfessor++;
    }
    
    public static void cadastrarAluno(Aluno aluno){
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
        
        alunos[quantidadeAluno] = aluno;
        quantidadeAluno++;
    }
    
    public static void excluirAlunoPorNome(String nomeAluno) {
        int i = 0;
        while(i < quantidadeAluno && !nomeAluno.equals(alunos[i].getNome())) {
            i++;
        }
        
        if (i < quantidadeAluno) {
            for (int k = i; k < quantidadeAluno; k++) {
                alunos[k] = alunos[k+1];
            }
            
            quantidadeAluno--;
        }
    }
    
    public static Aluno[] listarAlunos() {
        System.out.println("");
        
        for (int i = 0; i < quantidadeAluno; i++) {
            System.out.printf("\nAluno[%d]: %s", i + 1, alunos[i].getNome());
        }
        
        System.out.println("");
        
        return alunos;
    }
    
    public static String relacionarProfessorADisciplina(Professor professor, Disciplina disciplina) {
        if (professor.getquantidadeDisciplinasMatriculadas() == 0) {
            System.out.println("\nDigite quantas disciplinas o professor deve ser relacionado: ");
            int quantidadeDisciplinas = sc.nextInt();
            clearBuffer(sc);
            
            professor.setQuantidadeDisciplinasMatriculadas(quantidadeDisciplinas);
        }
        
        return professor.fazMatricula(disciplina);
    }
            
    public static String matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina) {
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
        for (int i = 0; i < quantidadeAluno; i++) {
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