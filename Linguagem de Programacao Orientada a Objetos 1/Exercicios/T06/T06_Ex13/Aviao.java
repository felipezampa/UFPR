/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public class Aviao extends VeiculoAereo {
    //ATRIBUTOS
    private float tamanhoAsas;
    //CONSTRUTORES
    public Aviao(float tamanhoAsas, int turbina, int helice) {
        super(turbina, helice);
        this.tamanhoAsas = 0;
    }
    public Aviao(float tamanhoAsas, int turbina, int helice, String marca, String modelo, String cor, String porte, float velocidade) {
        super(turbina, helice, marca, modelo, cor, porte, velocidade);
        this.tamanhoAsas = tamanhoAsas;
    }
    //METODOS
    public float getTamanhoAsas() {
        return tamanhoAsas;
    }

    public void setTamanhoAsas(float tamanhoAsas) {
        this.tamanhoAsas = tamanhoAsas;
    }
    
    @Override
    public void andar() {
        System.out.println("\nO avião está decolando da pista!");
    }
    
}
