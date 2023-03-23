/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 7 exercicio 2
 */
package T07_Ex2;

public class Bicicleta {
    
    int cadencia = 0;
    int velocidade = 0;
    int marcha = 1;
    
    public Bicicleta(int RPM, int velocidade, int marcha)throws RpmException, VelocidadeException, MarchaException {
        setRPM(RPM);
        setVelocidade(velocidade);
        setMarcha(marcha);
    }

    public void setRPM(int novoValor) throws RpmException {
        if (novoValor >= 0) {
            cadencia = novoValor;
        } else {
            throw new RpmException();
        }
    }
    
    public void setVelocidade(int novoValor) throws VelocidadeException {
        if (novoValor >= 0 && novoValor <= 100) {
            velocidade = novoValor;
        } else {
            throw new VelocidadeException();
        }
    }
    
    public void setMarcha(int novoValor) throws MarchaException {
        if (novoValor >= 0 && novoValor <= 24) {
            marcha = novoValor;
        } else {
            throw new MarchaException();
        }
    }

    void aumentarVelocidade(int incremento){
        velocidade = velocidade + incremento;
    }
    void aplicarFreios(int decremento){
        velocidade = velocidade - decremento;
    }
    void printStates(){
        System.out.println("cadencia="+cadencia+" velocidade="+velocidade+"marcha="+marcha);
    }
}

