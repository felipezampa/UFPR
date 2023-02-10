/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 5 exercicio 4
 */
package T05_Ex4;

public class Bicicleta {
    
    private int cadencia = 0;
    private int velocidade = 0;
    private int marcha = 1;
    private int maxmarcha = 0;
    
    Bicicleta(int maxmarcha){
        this.maxmarcha = maxmarcha;
        velocidade = 0;
        cadencia = 0;
    }
    
    Bicicleta(){
        this.maxmarcha = 18;
        velocidade = 0;
        cadencia = 0;
    }
    
    void mudarCadencia(int novoValor){
        cadencia = novoValor;
    }
    
    void mudarMarcha(int novoValor){
        if (novoValor <= maxmarcha){
            marcha = novoValor;
        }
        else{
            System.out.println("Não foi possível mudar a marcha");
            marcha = maxmarcha;
        }
    }
    
    void aumentarVelocidade(int incremento){
        velocidade = velocidade + incremento;
    }
    
    void aplicarFreios(int decremento){
        velocidade = velocidade - decremento;
    }
    
    void printStates(){
        System.out.println( "cadencia= "+cadencia+"\nvelocidade= "+velocidade+"\nmarcha= "+marcha );
    }

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getMarcha() {
        return marcha;
    }

    public void setMarcha(int marcha) {
        this.marcha = marcha;
    }

    public int getMaxmarcha() {
        return maxmarcha;
    }

    public void setMaxmarcha(int maxmarcha) {
        this.maxmarcha = maxmarcha;
    }
    
    
}
    

