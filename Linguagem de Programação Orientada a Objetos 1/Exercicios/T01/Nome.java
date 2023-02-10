public class Nome{
    public static void main (String args[]){
        int a = args.length - 1;
        for (int i = 0; i < args.length; i++){
          System.out.println (args[a]);
          a = a - 1;
        }
    }
}

