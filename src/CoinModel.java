import java.util.Map;

public record CoinModel(String baseCoin, Map<String, Float> conversionRates) {
}
