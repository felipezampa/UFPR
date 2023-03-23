/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public class Triangulo extends Forma {
    
    public Triangulo(double l1, double l2, double l3) {
        super(3);
        setMedida(0, l1); 
        setMedida(1, l2); 
        setMedida(2, l3);
    }
    
    @Override
    public double area() {
        double sp = (getMedida(0) + getMedida(1) + getMedida(2))/2;
        double aux = sp * (sp - getMedida(0)) * (sp - getMedida(1)) * (sp - getMedida(2));
        return Math.sqrt(aux);
    }
    
}
