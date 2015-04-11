package sidakejphes.nl.avans.edu.parsers;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.util.Log;

import sidakejphes.nl.avans.edu.models.Serie;

public class SeriesParser {
    public static ArrayList<Serie> parse(InputStream is) {
        ArrayList<Serie> series = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAXXMLHandler
            SeriesHandler saxHandler = new SeriesHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(saxHandler);
            // the process starts
            xmlReader.parse(new InputSource(is));
            // get the `Employee list`
            series = saxHandler.getSeries();

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("XML", "SAXXMLParser: parse() failed");
        }

        // return Employee list
        return series;
    }
}