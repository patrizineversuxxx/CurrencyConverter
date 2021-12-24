package converter;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class Main {



    public static double forintToCrones(double forintCount) {

        return 0;
    }



    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException {

        ExcangeRateParser parser = new ExcangeRateParser();
        FileInputStream stream = new FileInputStream("src/study/courses.xml");
        var locale = Locale.getDefault();
        parser.parse(stream, locale);
        //System.out.printf("One hungrian forint costs %.2f norwegian crones.", forintToCrones(1));
    }
}