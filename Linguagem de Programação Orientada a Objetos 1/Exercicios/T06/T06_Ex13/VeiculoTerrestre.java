/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public abstract class VeiculoTerrestre extends Veiculo{
    //ATRIBUTOS
    private String placa;
    private int roda;
    
    //CONSTRUTORES
    public VeiculoTerrestre(String placa, int roda) {
        this.placa = ("Indefinido");
        this.roda = 0;
    }

    public VeiculoTerrestre(String placa, int roda, String marca, String modelo, String cor, String porte, float velocidade) {
        super(marca, modelo, cor, porte, velocidade);
        this.placa = placa;
        this.roda = roda;
    } 
    
    //METODOS
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getRoda() {
        return roda;
    }

    public void setRoda(int roda) {
        this.roda = roda;
    }
    @Override
    public abstract void andar();
}