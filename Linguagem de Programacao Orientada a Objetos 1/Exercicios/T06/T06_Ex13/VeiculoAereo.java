/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;
    
public abstract class VeiculoAereo extends Veiculo {
    //ATRIBUTOS
    private int turbina;
    private int helice;
    //CONSTRUTORES
    public VeiculoAereo(int turbina, int helice) {
        this.turbina = 0;
        this.helice = 0;
    }
    public VeiculoAereo(int turbina, int helice, String marca, String modelo, String cor, String porte, float velocidade) {
        super(marca, modelo, cor, porte, velocidade);
        this.turbina = turbina;
        this.helice = helice;
    }
    
    //METODOS

    public int getTurbina() {
        return turbina;
    }

    public void setTurbina(int turbina) {
        this.turbina = turbina;
    }

    public int getHelice() {
        return helice;
    }

    public void setHelice(int helice) {
        this.helice = helice;
    }
    
    @Override
    public abstract void andar();
}
