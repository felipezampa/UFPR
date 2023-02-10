/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 13
 */
package T06_Ex13;

import java.util.*;

public class UsaVeiculo {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Veiculo[] listaVeiculos;
        String tipoVeiculo, marca, modelo, cor, porte, placa, tipoCambio, tipoCarga;
        int qtdVeiculos, qtdRoda, qtdPorta, qtdVagao, qtdTurbina, qtdHelice, qtdLugares;
        float velocidade, tamanhoAsas;
        boolean temLeme, temVela, estaSubmerso;

        System.out.println("Digite quantos veículos deseja criar: ");
        qtdVeiculos = sc.nextInt();
        clearBuffer(sc);
        
        listaVeiculos = new Veiculo[qtdVeiculos];
        
        for (int i = 0; i < qtdVeiculos; i++) {
            System.out.println("\n\nQual tipo de veículo deseja criar? Temos Carro, Trem, Avião e Barco: ");
            tipoVeiculo = sc.nextLine();
            
            tipoVeiculo = tipoVeiculo.toLowerCase();
            
            if (tipoVeiculo.equalsIgnoreCase("carro") || 
                tipoVeiculo.equalsIgnoreCase("trem")  || 
                tipoVeiculo.equalsIgnoreCase("aviao") || 
                tipoVeiculo.equalsIgnoreCase("barco")) {
                System.out.println("Digite a marca do " + tipoVeiculo + ": ");
                marca = sc.nextLine();

                System.out.println("Digite o modelo do " + tipoVeiculo + ": ");
                modelo = sc.nextLine();

                System.out.println("Digite a cor do " + tipoVeiculo + ": ");
                cor = sc.nextLine();

                System.out.println("Digite o porte do " + tipoVeiculo + " (pequeno/médio/grande): ");
                porte = sc.nextLine();

                System.out.println("Digite a velocidade atual do " + tipoVeiculo + ": ");
                velocidade = sc.nextFloat();
                
                switch(tipoVeiculo) {
                    case "carro":
                        clearBuffer(sc);
                        System.out.println("Digite a placa do carro: ");
                        placa = sc.nextLine();

                        System.out.println("Digite a quantidade de rodas do carro: ");
                        qtdRoda = sc.nextInt();

                        System.out.println("Digite a quantidade de portas do carro: ");
                        qtdPorta = sc.nextInt();
                        clearBuffer(sc);
                        
                        System.out.println("Digite o tipo de Cambio do carro: ");
                        tipoCambio = sc.nextLine();
                        clearBuffer(sc);

                        listaVeiculos[i] = new Carro(qtdPorta, tipoCambio, placa, qtdRoda, marca, modelo, cor, porte, velocidade);
                    break;
                    case "trem":
                        clearBuffer(sc);
                        System.out.println("Digite a placa do trem: ");
                        placa = sc.nextLine();

                        System.out.println("Digite a quantidade de rodas do trem: ");
                        qtdRoda = sc.nextInt();
                        clearBuffer(sc);

                        System.out.println("Digite a quantidade de vagões do trem: ");
                        qtdVagao = sc.nextInt();
                        clearBuffer(sc);
                        
                        System.out.println("Digite o tipo de carga do trem: ");
                        tipoCarga = sc.nextLine();
                        clearBuffer(sc);

                        listaVeiculos[i] = new Trem(qtdVagao, tipoCarga, placa, qtdRoda, marca, modelo, cor, porte, velocidade);
                    break;
                    case "aviao":
                        clearBuffer(sc);
                        System.out.println("Digite a quantidade de turbinas do avião: ");
                        qtdTurbina = sc.nextInt();
                        
                        System.out.println("Digite a quantidade de hélices do avião: ");
                        qtdHelice = sc.nextInt();
                        clearBuffer(sc);

                        System.out.println("Digite o tamanho da asa do avião: ");
                        tamanhoAsas = sc.nextFloat();
                        clearBuffer(sc);
  
                        listaVeiculos[i] = new Aviao(tamanhoAsas, qtdTurbina, qtdHelice, marca, modelo, cor, porte, velocidade);
                    break;
                    case "barco":
                        clearBuffer(sc);
                        System.out.println("Digite se o barco tem leme: ");
                        temLeme = sc.nextBoolean();
                        clearBuffer(sc);
                        
                        System.out.println("Digite se o barco tem vela: ");
                        temVela = sc.nextBoolean();
                        clearBuffer(sc);

                        System.out.println("Digite se o barco está submerso: ");
                        estaSubmerso = sc.nextBoolean();
                        clearBuffer(sc);

                        System.out.println("Digite a quantidade de lugares no barco: ");
                        qtdLugares = sc.nextInt();
                        clearBuffer(sc);

                        listaVeiculos[i] = new Barco(qtdLugares, temLeme, temVela, estaSubmerso, marca, modelo, cor, porte, velocidade);
                    break;
                    default:
                        System.out.println("Digite um veículo válido!!!");
                }
            } 
            else {
                System.out.println("Digite um veículo válido!");
            }
        }
        
        for (int i = 0; i < qtdVeiculos; i++) {
            listaVeiculos[i].andar();
        }  
    }
    
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }    
}
