package сonverter;

import java.util.HashMap;

public class CurrencyConverter {
    private HashMap<String, Double> excangeRates;

    private static boolean isValidAmount(Double amount) {
        return !(amount.isNaN() || amount < 0 || amount.isInfinite());
    }

    private static boolean isValidRate(Double rate) {
        return isValidAmount(rate) && rate != 0.0;
    }

    public CurrencyConverter(HashMap<String, Double> excangeRates) throws IllegalArgumentException {
        this.excangeRates = new HashMap<>();
        for (var entry : excangeRates.entrySet())
            if (isValidRate(entry.getValue()))
                this.excangeRates.put(entry.getKey(), entry.getValue());

        if (this.excangeRates.isEmpty())
            throw new IllegalArgumentException("Map is empty!");

    }

    public double convert(String originCurrency, String targetCurrency, double amount) {
        if (!isValidAmount(amount))
            throw new IllegalArgumentException("Invalid amount");

        if (!excangeRates.containsKey(originCurrency) || !excangeRates.containsKey(targetCurrency))
            throw new IllegalArgumentException("Currency value not exist");

        var originCurrencyValue = excangeRates.get(originCurrency);
        var targetCurrencyValue = excangeRates.get(targetCurrency);

        return originCurrencyValue * amount / targetCurrencyValue;
    }
}
