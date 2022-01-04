package сonverter.test;

import org.junit.jupiter.api.Test;
import сonverter.CurrencyConverter;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyConverterTest {

    public static HashMap<String, Double> buildEmptyExchangeRatesMap() {
        return new HashMap<>();
    }

    public static HashMap<String, Double> buildValidExchangeRatesMap() {
        var exchangeRates = new HashMap<String, Double>();
        exchangeRates.put("KEK", 10.0);
        exchangeRates.put("SHR", 5.0);
        exchangeRates.put("RUB", 1.0);
        exchangeRates.put("HEH", 2.0);
        return exchangeRates;
    }

    public static HashMap<String, Double> buildInvalidExchangeRatesMap() {
        var exchangeRates = new HashMap<String, Double>();
        exchangeRates.put("ZRO", 0.0);
        exchangeRates.put("NEG", -1.0);
        exchangeRates.put("INF", Double.POSITIVE_INFINITY);
        exchangeRates.put("NAN", Double.NaN);
        return exchangeRates;
    }

    public static HashMap<String, Double> buildMixedExchangeRatesMap() {
        var exchangeRates = new HashMap<String, Double>();
        exchangeRates.put("KEK", 10.0);
        exchangeRates.put("SHR", 5.0);
        exchangeRates.put("RUB", 1.0);
        exchangeRates.put("HEH", 2.0);
        exchangeRates.put("ZRO", 0.0);
        exchangeRates.put("NEG", -1.0);
        exchangeRates.put("INF", Double.POSITIVE_INFINITY);
        exchangeRates.put("NAN", Double.NaN);
        return exchangeRates;
    }

    @Test
    void converterConstructorThrowsIllegalArgumentExceptionForEmptyMap() {
        assertThrows(IllegalArgumentException.class, () -> new CurrencyConverter(buildEmptyExchangeRatesMap()));
    }

    @Test
    void converterConstructorThrowsIllegalArgumentExceptionForInvalidMap() {
        assertThrows(IllegalArgumentException.class, () -> new CurrencyConverter(buildInvalidExchangeRatesMap()));
        assertThrows(NullPointerException.class, () -> new CurrencyConverter(null));
    }

    @Test
    void converterConstructorDoesNotThrowExceptionForMixedMap() {
        new CurrencyConverter(buildMixedExchangeRatesMap());
    }

    @Test
    void converterReturnsCorrectResultsForValidInput() {
        var currencyConverter = new CurrencyConverter(buildValidExchangeRatesMap());
        assertEquals(currencyConverter.convert("KEK", "SHR", 0.5), 1);
        assertEquals(currencyConverter.convert("SHR", "KEK", 2), 1);
        assertEquals(currencyConverter.convert("RUB", "HEH", 1), 0.5);
        assertEquals(currencyConverter.convert("RUB", "HEH", 0), 0);
    }

    @Test
    void converterThrowsIllegalArgumentExceptionForInvalidCurrency() {
        var currencyConverter = new CurrencyConverter(buildValidExchangeRatesMap());
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert(null, null, 1));
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("SEX", "HEH", 1));
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("HEH", "SEX", 1));
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("XES", "SEX", 1));
    }

    @Test
    void converterThrowsIllegalArgumentExceptionForInvalidAmount() {
        var currencyConverter = new CurrencyConverter(buildValidExchangeRatesMap());
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("KEK", "SHR", -1));
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("KEK", "SHR", Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> currencyConverter.convert("KEK", "SHR", Double.NaN));
    }

}
