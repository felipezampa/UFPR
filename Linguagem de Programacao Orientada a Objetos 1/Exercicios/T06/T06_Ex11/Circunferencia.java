/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 11
 */
package T06_Ex11;

public class Circunferencia extends Forma implements Superficie {
    
    public Circunferencia(double raio) {
        super(1);
        setMedida(0, raio);
    }
    
    @Override
    public double area() {
        return Math.PI * Math.pow(getMedida(0), 2);
    }
    
}
