/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public abstract class VeiculoAquatico extends Veiculo{
    //ATRIBUTOS
    private boolean leme;
    private boolean vela;
    private boolean submerso;
    
    //CONSTRUTORES
    public VeiculoAquatico(boolean leme, boolean vela, boolean submerso) {
        this.leme = false;
        this.vela = false;
        this.submerso = false;
    }
    public VeiculoAquatico(boolean leme, boolean vela, boolean submerso, String marca, String modelo, String cor, String porte, float velocidade) {
        super(marca, modelo, cor, porte, velocidade);
        this.leme = leme;
        this.vela = vela;
        this.submerso = submerso;
    }
    
    //METODOS
    public boolean isLeme() {
        return leme;
    }

    public void setLeme(boolean leme) {
        this.leme = leme;
    }

    public boolean isVela() {
        return vela;
    }

    public void setVela(boolean vela) {
        this.vela = vela;
    }

    public boolean isSubmerso() {
        return submerso;
    }

    public void setSubmerso(boolean submerso) {    
        this.submerso = submerso;
    }

    @Override
    public abstract void andar();
}
