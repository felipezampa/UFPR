/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public class Barco extends VeiculoAquatico{
    //ATRIBUTOS
    private int lugares;
    
    //CONSTRUTORES
    public Barco(int lugares, boolean leme, boolean vela, boolean submerso) {
        super(leme, vela, submerso);
        this.lugares = lugares;
    }

    public Barco(int lugares, boolean leme, boolean vela, boolean submerso, String marca, String modelo, String cor, String porte, float velocidade) {
        super(leme, vela, submerso, marca, modelo, cor, porte, velocidade);
        this.lugares = lugares;
    }

    //METODOS
    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    @Override
    public void andar() {
        System.out.println("\nO barco está atravessando o rio!");
    }
    
}
