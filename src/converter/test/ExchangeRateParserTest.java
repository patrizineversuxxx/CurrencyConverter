package сonverter.test;

import org.junit.jupiter.api.Test;
import сonverter.ExchangeRateParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangeRateParserTest {
    static InputStream buildStreamForText(String text) throws IOException {
        return new BufferedInputStream(new URL(text).openStream());
    }

    @Test
    void parserThrowsURLException() {
        var exchangeRateParser = new ExchangeRateParser(Locale.FRENCH);
        assertThrows(MalformedURLException.class, () -> exchangeRateParser.parse(buildStreamForText("")));
    }
}