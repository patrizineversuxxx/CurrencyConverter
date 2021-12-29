package сonverter;

import java.util.HashMap;

public class CurrencyConverter {
    private HashMap <String, Double> excangeRates;

    public CurrencyConverter(HashMap<String, Double> excangeRates) {
        this.excangeRates = excangeRates;
    }

    public double convert (String originCurrency, String targetCurrency, double amount){
        var originCurrencyValue = excangeRates.get(originCurrency);
        var targetCurrencyValue = excangeRates.get(targetCurrency);

        return originCurrencyValue * amount / targetCurrencyValue;
    }
}
