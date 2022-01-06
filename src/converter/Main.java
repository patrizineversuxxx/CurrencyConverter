package сonverter;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {

    private static class Configuration {
        @SerializedName("url")
        public URL apiUrl;
        @SerializedName("locale")
        public Locale locale;

        public Configuration(String url, String locale) throws MalformedURLException {
            this.apiUrl = new URL(url);
            this.locale = Locale.forLanguageTag(locale);
        }

        public static Configuration parse(FileReader stream) {
            var gson = new Gson();
            var config = gson.fromJson(stream, Configuration.class);
            return config;
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException, IllegalArgumentException {
        if (args.length < 3) throw new IllegalArgumentException("Not enough arguments");
        var originCurrency = args[0];
        var targetCurrency = args[1];
        var amount = Double.parseDouble(args[2]);
        var configStream = new FileReader("config.json");
        var config = Configuration.parse(configStream);
        configStream.close();

        var parser = new ExchangeRateParser(config.locale);
        var stream = new BufferedInputStream(config.apiUrl.openStream());
        var exchangeRates = parser.parse(stream);
        stream.close();

        var converter = new CurrencyConverter(exchangeRates);
        var result = converter.convert(originCurrency, targetCurrency, amount);
        var message = MessageFormat.format("{0} {1} = {2} {3}", amount, originCurrency, result, targetCurrency);
        System.out.println(message);
    }
}