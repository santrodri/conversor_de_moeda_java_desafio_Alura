import java.util.List;
import java.util.Scanner;


class Main{
    public static void main() {
        List<String> moedas = Cotacao.obtemMoedas();
        IO.println("Seja bem vindo ao sistema de conveção monetaria");
        IO.println("Escolha um numero para proceguir-mos:");

        Scanner scanner = new Scanner(System.in);

        float valor;
        String moeda;
        int opcao;
        TranformaEntrada tranformaEntrada = new TranformaEntrada(moedas);

        do {
            for (int i = 0; i < moedas.size(); i++ ){
                IO.println(String.format("%d - BRL =>> %s", i+1, moedas.get(i)));
            }
            IO.println("0 - Sair do sistema");

            opcao = tranformaEntrada.validaEntrada(scanner.next());
            moeda = tranformaEntrada.capturaMoeda(opcao);

            if (moeda.isBlank()){
                IO.println("Escolha uma opção válida");
                pause();
                continue;
            }

            IO.println("Quanto voce deseja converter?");
            valor = scanner.nextLong();
            IO.println(String.format("Isso vai dar %.2f %s", Cotacao.converte(valor, moeda), moeda));
            pause();

        } while (opcao != 7);

    }

    public static void pause(){
        IO.println("Pressione enter para continuar");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}