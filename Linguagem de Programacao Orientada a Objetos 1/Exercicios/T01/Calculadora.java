public class Calculadora {
    public static void main(String args[]) {
        
        float priNumero = Float.parseFloat(args[0]);
        float segNumero = Float.parseFloat(args[1]);
        String op = (args[2]);
        float resultado = 0;
        
            switch (op){
            case "+": 
               resultado = priNumero + segNumero;
               break;
            case "-":
               resultado = priNumero - segNumero;
               break;
            case "X":
               resultado = priNumero * segNumero;
               break;
            case "/":
               resultado = priNumero / segNumero;
               break;
            }
        System.out.println(resultado);
    }
}
