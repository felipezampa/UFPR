/**
 *
 * @author Felipe Zampa
 */
package exercicio1;

import java.util.ArrayList;

public class IntegrantesEquipe {

    public String nome;
    public String social;

    public IntegrantesEquipe(String nome, String social) {
        this.nome = nome;
        this.social = social;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public static ArrayList<IntegrantesEquipe> getIntegrantes() {
        ArrayList<IntegrantesEquipe> integrantes = new ArrayList<>();
        integrantes.add(new IntegrantesEquipe("FELIPE AUGUSTO VIEIRA ZAMPA", "https://github.com/FelipeZampa"));
        integrantes.add(new IntegrantesEquipe("ARTHUR NASCIMENTO", "https://github.com/TuiNascimento"));
        integrantes.add(new IntegrantesEquipe("MATHEUS ALVES CHAVES", "https://github.com/matheus-a-chaves"));
        integrantes.add(new IntegrantesEquipe("MATEUS IRINEU GUIMARÃES MALLMANN", "https://github.com/mateusguimaraesmallmann"));
        return integrantes;
    }

}
