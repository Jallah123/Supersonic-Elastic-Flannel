package sidakejphes.nl.avans.edu.parsers;

/**
 * Created by Jelle on 18-3-2015.
 */

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sidakejphes.nl.avans.edu.models.Serie;

public class SeriesHandler extends DefaultHandler {

    private ArrayList<Serie> series;
    private String tempVal;
    private Serie tempSerie;
    private Boolean episodesBegon = false;

    public SeriesHandler() {
        series = new ArrayList<Serie>();
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    // Event Handlers
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("series")) {
            // create a new instance of employee
            tempSerie = new Serie();
        } else if (qName.equalsIgnoreCase("episode")) {
            episodesBegon = true;
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (!episodesBegon) {
            if (qName.equalsIgnoreCase("series")) {
                // add it to the list
                series.add(tempSerie);
            } else if (qName.equalsIgnoreCase("seriesid")) {
                tempSerie.setSeriesid(Integer.parseInt(tempVal));
            } else if (qName.equalsIgnoreCase("SeriesName")) {
                tempSerie.setSeriesName(tempVal);
            } else if (qName.equalsIgnoreCase("language")) {
                tempSerie.setLanguage(tempVal);
            } else if (qName.equalsIgnoreCase("banner")) {
                tempSerie.setBanner(tempVal);
            } else if (qName.equalsIgnoreCase("Overview")) {
                tempSerie.setOverview(tempVal);
            } else if (qName.equalsIgnoreCase("FirstAired")) {
                tempSerie.setFirstAired(tempVal);
            } else if (qName.equalsIgnoreCase("Network")) {
                tempSerie.setNetwork(tempVal);
            } else if (qName.equalsIgnoreCase("IMDB_ID")) {
                tempSerie.setIMDB_ID(tempVal);
            } else if (qName.equalsIgnoreCase("id")) {
                tempSerie.setId(tempVal);
            } else if (qName.equalsIgnoreCase("rating")) {
                tempSerie.setRating(Float.parseFloat(tempVal));
            }
        }
    }
}

