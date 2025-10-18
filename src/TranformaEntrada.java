import java.util.List;

public class TranformaEntrada {
    private List<String> moedas;

    public TranformaEntrada(List<String> moedas){this.moedas = moedas;}

    public String capturaMoeda(int valor){
        try {
            return moedas.get(valor - 1);
        }catch (Exception e){
            return "";
        }
    }

    public int validaEntrada(String entrada){
        try {
            return Integer.parseInt(entrada);
        }catch (NumberFormatException e) {
            IO.println("insira um valor válido");
        }

        return 0;
    }
}
