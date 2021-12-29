package сonverter;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException {

        var originCurrency = "HUF";
        var targetCurrency = "NOK";
        var amount = 1;

        var locale = Locale.getDefault();
        var parser = new ExcangeRateParser(locale);
        var stream = new BufferedInputStream(new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream());
        //var stream = new BufferedInputStream(new URL("file:///F:\\Projects\\IdeaProjects\\src\\converter\\courses.xml").openStream());


        var exchangeRates = parser.parse(stream);
        stream.close();

        var converter = new CurrencyConverter(exchangeRates);
        var result = converter.convert(originCurrency, targetCurrency, amount);
        var message = MessageFormat.format("{0} {1} = {2} {3}", amount, originCurrency, result, targetCurrency);
        System.out.println(message);

    }
}