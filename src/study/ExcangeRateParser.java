package study;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

public class ExcangeRateParser {

    public ExcangeRateParser() {}

    public HashMap<String, Double> parse(InputStream stream, Locale locale) throws ParserConfigurationException, IOException, SAXException, ParseException {
        var exchangeRates = new HashMap<String, Double>();
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();
        var document = builder.parse(stream);

        var currencyList = document.getElementsByTagName("Valute");

        for (int i = 0; i < currencyList.getLength(); i++) {
            var currencyNode = currencyList.item(i);
            var currencyElement = (Element) currencyNode;

            var code = currencyElement.getElementsByTagName("CharCode").item(0);
            var charCode = code.getTextContent();

            var amountNode = currencyElement.getElementsByTagName("Nominal").item(0);
            var amount = Integer.parseInt(amountNode.getTextContent());

            var valueNode = currencyElement.getElementsByTagName("Value").item(0);
            var stringValue = valueNode.getTextContent();
            var format = NumberFormat.getInstance(locale);
            var value = (Double) format.parse(stringValue) / amount;

            exchangeRates.put(charCode, value);
        }
        System.out.println(exchangeRates.get("NOK"));
        return exchangeRates;
    }
}
