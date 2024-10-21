package br.com.pedro.TabelaFipe.main;


import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    Requisicoes requisicoes = new Requisicoes();
    Scanner leitura = new Scanner(System.in);
    Integer tipoVeiculo = null;

    public void exibeMenu() throws JsonProcessingException {
        System.out.println("\n---- OPÇÕES ----\n");
        System.out.println("1 - Carros");
        System.out.println("2 - Motos");
        System.out.println("3 - Caminhões\n");

        try {
            System.out.println("Qual tipo de veículo deseja procurar?");
            tipoVeiculo = leitura.nextInt();leitura.nextLine();

        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("\nEssa opção não é válida.");
            System.exit(0);
        }

        switch (tipoVeiculo) {
            case 1: {
                requisicoes.buscarVeiculo("carros");
                break;
            }
            case 2: {
                requisicoes.buscarVeiculo("motos");
                break;
            }
            case 3: {
                requisicoes.buscarVeiculo("caminhoes");
                break;
            }
            default: {
                System.out.println("\nEssa opção não é válida.");
                System.exit(0);
            }
        }

    }
}
