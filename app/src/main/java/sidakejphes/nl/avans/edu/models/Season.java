package sidakejphes.nl.avans.edu.models;

import java.util.ArrayList;

/**
 * Created by Tojba on 3/20/2015.
 */
public class Season {
    ArrayList<Episode> episodes = new ArrayList<Episode>();

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}
