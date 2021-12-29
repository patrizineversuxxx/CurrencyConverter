package сonverter;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException {

        var originCurrency = "HUF";
        var targetCurrency = "NOK";
        var amount = 1;

        var parser = new ExcangeRateParser();
        var stream = new FileInputStream("src/converter/courses.xml");
        var locale = Locale.getDefault();
        var exchangeRates = parser.parse(stream, locale);

        var converter = new Converter(exchangeRates);
        var result = converter.convert(originCurrency, targetCurrency, amount);
        var message = MessageFormat.format("{0} {1} = {2} {3}", amount, originCurrency, result, targetCurrency);
        System.out.println(message);

    }
}