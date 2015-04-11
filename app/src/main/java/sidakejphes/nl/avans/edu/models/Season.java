package sidakejphes.nl.avans.edu.models;

import java.util.ArrayList;

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
