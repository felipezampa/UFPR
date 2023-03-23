/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public class Retangulo extends Forma {
    
    public Retangulo(double altura, double largura) {
        super (2);
        setMedida(0, altura); 
        setMedida(1, largura);
    }

    @Override
    public double area(){
        return getMedida(0) * getMedida(1);
    }
}
