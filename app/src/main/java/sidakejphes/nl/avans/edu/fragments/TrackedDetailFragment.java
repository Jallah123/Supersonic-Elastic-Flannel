package sidakejphes.nl.avans.edu.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Set;

import sidakejphes.nl.avans.edu.models.Season;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.wherewasi.R;

public class TrackedDetailFragment extends Fragment {

    SharedPreferences prefs;
    Gson g = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("series", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trackeddetailfragment_layout, container, false);
    }

    public void updateSerie(Serie tempSerie){
        Set<String> seriesSet = prefs.getStringSet("series", null);
        for(String s: seriesSet){
            Serie serie = g.fromJson(s, Serie.class);
            if(tempSerie.getSeriesid().equals(serie.getSeriesid())){
                tempSerie = serie;
                break;
            }
        }
        fillView(tempSerie);
    }
    private void fillView(Serie tempSerie){
        TextView seriesName = (TextView) getView().findViewById(R.id.tracked_detail_seriesName);
        seriesName.setText(tempSerie.getSeriesName());
        TextView amountOfSeasons = (TextView) getView().findViewById(R.id.tracked_seasons_amount);
        amountOfSeasons.setText("Amount of seasons : " + String.valueOf(tempSerie.getSeasons().size()));
        TextView amountOfEpisodes = (TextView) getView().findViewById(R.id.tracked_episodes_amount);
        int amount = 0;
        for (Season s : tempSerie.getSeasons()) {
            amount += s.getEpisodes().size();
        }
        amountOfEpisodes.setText("Total episodes : " + amount);
        TextView releaseDate = (TextView) getView().findViewById(R.id.tracked_release_date);
        releaseDate.setText("First aired : " + tempSerie.getFirstAired());
        TextView overview = (TextView) getView().findViewById(R.id.tracked_overview);
        overview.setText("Summary : " + tempSerie.getOverview());
        TextView rating = (TextView) getView().findViewById(R.id.tracked_rating);
        rating.setText("Rating : " + tempSerie.getRating() + "/10");
        TextView status = (TextView) getView().findViewById(R.id.tracked_status);
        status.setText("Status : " + tempSerie.getStatus());
    }
}
