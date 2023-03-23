/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public class Trem extends VeiculoTerrestre{
    //ATRIBUTOS
    private int quantidadeVagao;
    private String tipoCarga;
    
    //CONSTRUTORES
    public Trem(int quantidadeVagao, int tipoCarga, String placa, int roda) {
        super(placa, roda);
        this.quantidadeVagao = 0;
        this.tipoCarga = ("Carga");
    }

    public Trem(int quantidadeVagao, String tipoCarga, String placa, int roda, String marca, String modelo, String cor, String porte, float velocidade) {
        super(placa, roda, marca, modelo, cor, porte, velocidade);
        this.quantidadeVagao = quantidadeVagao;
        this.tipoCarga = tipoCarga;
    }
    
    //METODOS
    public int getQuantidadeVagao() {
        return quantidadeVagao;
    }

    public void setQuantidadeVagao(int quantidadeVagao) {
        this.quantidadeVagao = quantidadeVagao;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
 
    @Override
    public void andar() {
        System.out.println("\nO trem está andando nos trilho!");
    }
    
}
