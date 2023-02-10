/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public class Carro extends VeiculoTerrestre{
    //ATRIBUTOS
    private int quantidadePortas;
    private String tipoCambio;
    
    //CONSTRUTORES
    public Carro(int quantidadePortas, String tipoCambio, String placa, int roda) {
        super(placa, roda);
        this.quantidadePortas = quantidadePortas;
        this.tipoCambio = tipoCambio;
    }

    public Carro(int quantidadePortas, String tipoCambio, String placa, int roda, String marca, String modelo, String cor, String porte, float velocidade) {
        super(placa, roda, marca, modelo, cor, porte, velocidade);
        this.quantidadePortas = quantidadePortas;
        this.tipoCambio = tipoCambio;
    }
           
    //METODOS
    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    @Override
    public void andar() {
        System.out.println("\nO carro está andando na estrada!");
    }
}
