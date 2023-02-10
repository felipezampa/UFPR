/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 5 exercicio 8
 */
package T05_Ex8;
public class celsiusFK {
    
    public static double celsiusFarenheit(double c) {
        return (9.0 * c)/(5.0 + 32.0);
    }
    public static double celsiusKelvin(double c){
        return c + 273;
    }
    public static double farenheitCelsius(double f){
        return 5.0*(f - 32.0) / 9.0;
    }
    public static double farenheitKelvin(double f){
        return ((f - 32) * 5.0/ 9.0) + 273.0;
    }
    public static double kelvinFarenheit(double k){
        return ((k - 273.0)* 9.0 / 5.0) + 32.0;
    }
    public static double kelvinCelsius(double k){
        return k - 273;
    }
    
}
