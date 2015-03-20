package sidakejphes.nl.avans.edu.models;

import java.util.ArrayList;

/**
 * Created by Tojba on 3/20/2015.
 */
public class Season {
    ArrayList<Episode> episodes = new ArrayList<Episode>();
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}
