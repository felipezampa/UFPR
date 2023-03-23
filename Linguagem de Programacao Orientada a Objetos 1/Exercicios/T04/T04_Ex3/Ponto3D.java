/*
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 *  Lista 4 exercicio 3
 */
package T04_Ex3;

public class Ponto3D {
    
    private double x;
    private double y;
    private double z;
    private String cor;
    private String intensidade;
     
    public double calculaDistancia(double x, double y, double z){
        return Math.sqrt (Math.pow (this.x - x, 2) + Math.pow (this.y - y, 2) + Math.pow (this.z - z, 2));
    }  
    
    public void setX(double x){
        this.x = x; 
    };
    
    public double getX(){
        return x;
    }
    
    public void setY(double y){
        this.y = y; 
    };
    
    public double getY(){
        return y;
    }
    
    public void setZ(double z){
        this.z = z; 
    };
    
    public double getZ(){
        return z;
    }
    
    public void setCor(String cor){
        this.cor = cor; 
    };
    
    public String getCor(){
        return cor;
    }
    
    public void setIntensidade(String intensidade){
        this.intensidade = intensidade; 
    };
    
    public String getIntensidade(){
        return intensidade;
    }
    
             
}
