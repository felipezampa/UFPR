/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public class Circunferencia extends Forma {
    
    public Circunferencia(double raio) {
        super(1);
        setMedida(0, raio);
    }
    
    @Override
    public double area() {
        return Math.PI * Math.pow(getMedida(0), 2);
    }
}
