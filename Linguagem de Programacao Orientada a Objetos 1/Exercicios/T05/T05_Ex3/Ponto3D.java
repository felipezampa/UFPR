/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 *  Lista 4 exercicio 3
 */
package T05_Ex3;

public class Ponto3D {
    
    private double x;
    private double y;
    private double z;
     
    protected double calculaDistancia(double x, double y, double z){
        return Math.sqrt (Math.pow (this.x - x, 2) + Math.pow (this.y - y, 2) + Math.pow (this.z - z, 2));
    }  
    
    protected void setX(double x){
        this.x = x; 
    };
    
    protected void setY(double y){
        this.y = y; 
    };
    
    protected void setZ(double z){
        this.z = z; 
    };
    
             
}