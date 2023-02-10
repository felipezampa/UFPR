/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 4 exercicio 9
 */
package T04_Ex9;

public class Circunferencia {
     
    private double raio;
    private double area;
    
    public void setRaio(double raio){
        this.raio = raio; 
    };
    
    public double getRaio(){
        return raio;
    }
    
    public double calculaArea(){
        area = Math.PI * (Math.pow(raio, 2));
        return area;
    };
    
}
