/**
 *
 * @author Felipe Zampa
 */
package exercicio1;

import java.util.ArrayList;

public class CursosSept {
    
    public String nome;
    public String link;

    public CursosSept(String nome, String link) {
        this.nome = nome;
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public static ArrayList<CursosSept> getCursos(){
        ArrayList<CursosSept> cursos = new ArrayList<>();
        cursos.add(new CursosSept("Especialização", "http://www.sept.ufpr.br/portal/especializacao"));
        cursos.add(new CursosSept("Pós-Graduação em Bioinformática", "http://www.bioinfo.ufpr.br/"));
        cursos.add(new CursosSept("TACS", "http://www.sept.ufpr.br/portal/agentesaude/sobre-o-curso/"));
        cursos.add(new CursosSept("TGP", "http://www.sept.ufpr.br/portal/gestaopublica/sobre-o-curso/"));
        cursos.add(new CursosSept("TADS", "http://www.sept.ufpr.br/portal/analisesistemas"));
        cursos.add(new CursosSept("TS", "http://www.sept.ufpr.br/portal/secretariado/sobre-o-curso/"));
        cursos.add(new CursosSept("TL", "http://www.sept.ufpr.br/portal/luteria/sobre-o-curso/"));        
        cursos.add(new CursosSept("TCI", "http://www.sept.ufpr.br/portal/comunicacaoinstitucional/sobre-o-curso/"));
        cursos.add(new CursosSept("TNI", "http://www.sept.ufpr.br/portal/negociosimobiliarios/sobre-o-curso/"));
        cursos.add(new CursosSept("TCQ", "http://www.sept.ufpr.br/portal/gestaoqualidade/sobre-o-curso/"));
        cursos.add(new CursosSept("TPG", "http://www.sept.ufpr.br/portal/petroleogas/sobre-o-curso/"));
        cursos.add(new CursosSept("TPC", "http://www.sept.ufpr.br/portal/producaocenica/"));
        
        return cursos;
    }
    

}
