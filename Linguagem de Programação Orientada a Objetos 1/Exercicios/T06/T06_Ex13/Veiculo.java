/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

public abstract class Veiculo {
    //ATRIBUTOS
    private String marca;
    private String modelo;
    private String cor;
    private String porte;
    private float velocidade;
    
    //CONSTRUTORES
    public Veiculo() {
        this.marca = ("indefinida");
        this.modelo = ("indefinido");
        this.cor = ("indefinida");
        this.porte = ("indefinido");
        this.velocidade = (0);
    }
    public Veiculo(String marca, String modelo, String cor, String porte, float velocidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.porte = porte;
        this.velocidade = velocidade;
    }
    
    //METODOS
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public String getPorte() {
        return porte;
    }
    
    public void setPorte(String porte) { 
        if (porte.equalsIgnoreCase("pequeno") || porte.equalsIgnoreCase("médio") || porte.equalsIgnoreCase("grande")) {
            this.porte = porte; 
        } else {
            this.porte = "indefinido";
        }
    }

    public abstract void andar();
}
