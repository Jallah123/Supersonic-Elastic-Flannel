package sidakejphes.nl.avans.edu.parsers;

/**
 * Created by Jelle on 18-3-2015.
 */

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sidakejphes.nl.avans.edu.models.Episode;
import sidakejphes.nl.avans.edu.models.Season;
import sidakejphes.nl.avans.edu.models.Serie;

public class SeriesHandler extends DefaultHandler {

    private ArrayList<Serie> series;
    private String tempVal;
    private Serie tempSerie;
    private Episode tempEpisode;
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
            tempEpisode = new Episode();
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
                series.add(tempSerie);
            } else if (qName.equalsIgnoreCase("seriesid")) {
                tempSerie.setSeriesid(tempVal);
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
            } else if(qName.equalsIgnoreCase("status")) {
                tempSerie.setStatus(tempVal);
            }
        } else {
            if (qName.equalsIgnoreCase("episode")) {
            } else if (tempVal == "") {
                return;
            } else if (qName.equalsIgnoreCase("id")) {
                tempEpisode.setId(Integer.parseInt(tempVal));
            } else if (qName.equalsIgnoreCase("episodename")) {
                tempEpisode.setName(tempVal);
            } else if (qName.equalsIgnoreCase("episodenumber")) {
                tempEpisode.setNumber(Integer.parseInt(tempVal));
            } else if (qName.equalsIgnoreCase("firstaired")) {
                tempEpisode.setFirstAired(tempVal);
            } else if (qName.equalsIgnoreCase("overview")) {
                tempEpisode.setOverview(tempVal);
            } else if (qName.equalsIgnoreCase("rating")) {
                tempEpisode.setRating(Float.parseFloat(tempVal));
            } else if (qName.equalsIgnoreCase("lastupdated")) {
                tempEpisode.setLastUpdated(tempVal);
            } else if (qName.equalsIgnoreCase("seasonid")) {
                tempEpisode.setSeasonId(Integer.parseInt(tempVal));
            } else if (qName.equalsIgnoreCase("seriesid")) {
                tempEpisode.setSeriesId(Integer.parseInt(tempVal));
            } else if (qName.equalsIgnoreCase("seasonnumber")){
                if(series.get(0).getSeasons().isEmpty()) {
                    Season s = new Season();
                    s.setNumber(Integer.parseInt(tempVal));
                    series.get(0).getSeasons().add(s);
                }
                Boolean seasonExists = false;
                for(Season s : series.get(0).getSeasons()) {
                    if(s.getNumber() == Integer.parseInt(tempVal)) {
                        s.getEpisodes().add(tempEpisode);
                        seasonExists = true;
                    }
                }

                if(!seasonExists) {
                    Season s = new Season();
                    s.setNumber(Integer.parseInt(tempVal));
                    series.get(0).getSeasons().add(s);
                    s.getEpisodes().add(tempEpisode);
                }
            }
        }
    }
}

