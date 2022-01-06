package сonverter.test;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;
import сonverter.ExchangeRateParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangeRateParserTest {

    static InputStream buildStreamForText(String text) {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void parserThrowsParseException() {
        var exchangeRateParser = new ExchangeRateParser(Locale.FRENCH);
        assertThrows(SAXParseException.class, () -> exchangeRateParser.parse(buildStreamForText("Welcome to the club, buddy!")));
    }

    @Test
    void parserThrowsIllegalArgumentException() {
        var exchangeRateParser = new ExchangeRateParser(Locale.FRENCH);
        assertThrows(IllegalArgumentException.class, () -> exchangeRateParser.parse(null));
    }
}