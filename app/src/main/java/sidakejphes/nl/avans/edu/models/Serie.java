package sidakejphes.nl.avans.edu.models;

/**
 * Created by Jelle on 11-3-2015.
 */
public class Serie {

    private int seriesid;
    private String language;
    private String SeriesName;
    private String banner;
    private String Overview;
    private String FirstAired;
    private String Network;
    private String IMDB_ID;
    private String id;

    public static Serie makeSeries(String xml){
        Serie s = new Serie();



        return s;
    }

    public int getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(int seriesid) {
        this.seriesid = seriesid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getFirstAired() {
        return FirstAired;
    }

    public void setFirstAired(String firstAired) {
        FirstAired = firstAired;
    }

    public String getNetwork() {
        return Network;
    }

    public void setNetwork(String network) {
        Network = network;
    }

    public String getIMDB_ID() {
        return IMDB_ID;
    }

    public void setIMDB_ID(String IMDB_ID) {
        this.IMDB_ID = IMDB_ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
