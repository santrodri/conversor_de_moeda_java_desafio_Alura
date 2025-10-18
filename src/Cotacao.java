import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Cotacao {
        private static String requisita(){
            String apiKey = System.getenv("API_KEY");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create(
                                    String.format("https://v6.exchangerate-api.com/v6/%s/latest/BRL", apiKey)
                            )
                    )
                    .GET()
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return response.body();
        }

    public static List<String> obtemMoedas(){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        CoinModel coinModel = gson.fromJson(Cotacao.requisita(), CoinModel.class);

        return new ArrayList<String>(coinModel.conversionRates().keySet());
    }

    public static float converte(float valor, String moeda){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        CoinModel coinModel = gson.fromJson(Cotacao.requisita(), CoinModel.class);

        return valor * coinModel.conversionRates().get(moeda);
    }
}
