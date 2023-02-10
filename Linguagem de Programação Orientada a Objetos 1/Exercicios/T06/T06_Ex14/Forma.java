/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 14
 */
package T06_Ex14;

public abstract class Forma {
    
    private double medida[];
    
    public Forma(int numMedidas) {
        medida = new double[numMedidas];
    }
    
    public double getMedida(int i) {
        if ((i > 0) || (i < medida.length)) {
            return medida[i];
        } else {
            return -1;
        }
    }
    
    protected void setMedida(int i, double m) {
        if ((i > 0) || (i < medida.length) || (m < 0)) {
            medida[i] = m;
        }
    }
    
    public String toString() {
        String aux = getClass().getSimpleName();
        
        aux = aux + "\nmedidas: ";
        for (int i = 0; i < medida.length - 1; i++) {
            aux = aux + medida[i] + ", ";
        }
        aux = aux + medida[medida.length - 1];
        
        return aux;
    }
    
    public int getNumMedidas() {
        return medida.length;
    }
    
    public abstract double area();
}
