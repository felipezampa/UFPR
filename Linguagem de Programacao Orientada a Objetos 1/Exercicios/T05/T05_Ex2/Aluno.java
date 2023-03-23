/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 *  Lista 5 exercicio 2
 */
package T05_Ex2;

public class Aluno {
    //ATRIBUTOS
    private String nome;
    private int idade;
    private String endereco;
    private String matricula;
    private String curso;
    private int periodo;
    private String[] disciplinasMatriculadas;
    private int quantidade = 0;
    
    //CONSTRUTOR    
    public Aluno(String nome,
                 int idade,
                 String endereco,
                 String matricula, 
                 String curso, 
                 int periodo, 
                 int quantidadeDisciplinasPermitidas) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.matricula = matricula;
        this.curso = curso;
        this.periodo = periodo;
        this.setQuantidadeDisciplinasPermitidas(quantidadeDisciplinasPermitidas);
    }
    
    //METODOS    
    public String fazMatricula(String disciplina) {
        String informe;
        
        if (this.disciplinasMatriculadas.length == 0) {
            informe = "\nEste aluno não pode ser matriculado em nenhuma disciplina, por favor, fale com a secretaria.\n\n";
            return informe;
        }
        
        if (this.quantidade >= this.disciplinasMatriculadas.length) {
            informe = "\nQuantidade de disciplinas excedida.\nO limite de disciplinas para este aluno é de " + disciplinasMatriculadas.length + " disciplina(s).\nSe desejar, cancele a matrícula de uma das disciplinas e faça a nova matrícula.\n\n";
            return informe;
        }
        
        this.disciplinasMatriculadas[this.quantidade] = disciplina;
        this.quantidade++;
        informe = "\nMatrícula na disciplina " + disciplina + " executada.\n\n";
        return informe;
    }
    
    public String cancelarMatricula(String disciplina) {
        String informe;
        int j, i = 0;
        while (i < this.quantidade && !disciplina.equals(disciplinasMatriculadas[i])) {
            i++;
        }
        
        if (i == this.quantidade) {
            informe = "\nAluno não está matriculado na disciplina " + disciplina + ", portanto não é possível cancelar esta matrícula.\n\n";
            return informe;
        }
   
        for (j = i; j < this.quantidade; j++) {
            if (j == this.quantidade - 1) {
                disciplinasMatriculadas[j] = null;
                break;
            }
            disciplinasMatriculadas[j] = disciplinasMatriculadas[j + 1];
        }
        this.quantidade--;
        informe = "\nCancelamento da matrícula da disciplina " + disciplina + " executado com sucesso.\n\n";
        return informe;
    }
    
    public String imprime() {
        String alunoAtributos, disciplinas = "";
        int i;
        for (i = 0; i < this.quantidade; i++) {
            disciplinas += disciplinasMatriculadas[i] + "; ";
        }
        
        alunoAtributos = "\n-----------------------------------------------------------------\n" + 
                         "Nome do Aluno: "              + this.nome      + 
                         "\nMatrícula: "                + this.matricula + 
                         "\nCurso: "                    + this.curso     +
                         "\nPeríodo: "                  + this.periodo   + 
                         "\nDisciplinas Matriculadas: " + disciplinas    + 
                         "\n-----------------------------------------------------------------\n";
        return alunoAtributos;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String[] getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public void setDisciplinasMatriculadas(String[] disciplinasMatriculadas) {
        this.disciplinasMatriculadas = disciplinasMatriculadas;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
 
    public int getquantidadeDisciplinasMatriculadas() {
        return this.quantidade;
    }
    
    public void setQuantidadeDisciplinasPermitidas(int quantidadeDisciplinasPermitidas) {
        this.disciplinasMatriculadas = new String[quantidadeDisciplinasPermitidas];
    }
    
}

