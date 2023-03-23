/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 3
 */
package T07_Ex3;

public class Retangulo {
    //ATRIBUTOS
    private double altura;
    private double largura;
    
    //CONSTRUTOR
    public Retangulo(){
    }
    public Retangulo(double altura, double largura) throws IllegalArgumentException {
        setTamanho(altura, largura);
    }
    //METODOS
    private void setTamanho(double altura, double largura) throws IllegalArgumentException {
        
        if(altura > 0.0 && largura > 0.0) {
            this.altura = altura;
            this.largura = largura;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }
    
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }
    
}
