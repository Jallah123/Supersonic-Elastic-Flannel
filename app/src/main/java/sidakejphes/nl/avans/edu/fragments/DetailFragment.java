package sidakejphes.nl.avans.edu.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;

import sidakejphes.nl.avans.edu.models.Season;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.parsers.SeriesParser;
import sidakejphes.nl.avans.edu.wherewasi.DataProvider;
import sidakejphes.nl.avans.edu.wherewasi.IResultHandler;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailFragment extends Fragment {

    private SharedPreferences prefs;
    private Gson g = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("series", getActivity().getApplicationContext().MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("DetailFragment", "Created fragment");
        return inflater.inflate(R.layout.detailfragment_layout, container, false);
    }

    public void updateSerie(Serie serie) {
        TextView t = (TextView) getView().findViewById(R.id.detail_seriesName);
        t.setText(serie.getSeriesName());
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        DataProvider.doGet(DataProvider.API_KEY + "/series/" + serie.getSeriesid() + "/all", new IResultHandler() {
            @Override
            public void onSuccess(InputStream result) {
                final ArrayList<Serie> series = SeriesParser.parse(result);
                if (series.size() != 1)
                    return;
                final Serie tempSerie = series.get(0);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView amountOfSeasons = (TextView) getView().findViewById(R.id.seasons_amount);
                        amountOfSeasons.setText("Amount of seasons : " + String.valueOf(tempSerie.getSeasons().size()));
                        TextView amountOfEpisodes = (TextView) getView().findViewById(R.id.episodes_amount);
                        int amount = 0;
                        for (Season s : tempSerie.getSeasons()) {
                            amount += s.getEpisodes().size();
                        }
                        amountOfEpisodes.setText("Total episodes : " + amount);
                        TextView releaseDate = (TextView) getView().findViewById(R.id.release_date);
                        releaseDate.setText("First aired : " + tempSerie.getFirstAired());
                        TextView overview = (TextView) getView().findViewById(R.id.overview);
                        overview.setText("Summary : " + tempSerie.getOverview());
                        TextView rating = (TextView) getView().findViewById(R.id.rating);
                        rating.setText("Rating : " + tempSerie.getRating() + "/10");
                        TextView status = (TextView) getView().findViewById(R.id.status);
                        status.setText("Status : " + tempSerie.getStatus());
                        prefs.edit().putString("currentSerie", g.toJson(tempSerie)).commit();
                        getActivity().findViewById(R.id.saveSerie).setVisibility(View.VISIBLE);
                        getActivity().findViewById(R.id.deleteSerie).setVisibility(View.VISIBLE);
                    }
                });
                progress.dismiss();
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

        });

    }

    public void clearView() {
        TextView t = (TextView) getView().findViewById(R.id.seasons_amount);
        t.setText("");
        t = (TextView) getView().findViewById(R.id.episodes_amount);
        t.setText("");
        t = (TextView) getView().findViewById(R.id.release_date);
        t.setText("");
        t = (TextView) getView().findViewById(R.id.overview);
        t.setText("");
        t = (TextView) getView().findViewById(R.id.rating);
        t.setText("");
        t = (TextView) getView().findViewById(R.id.status);
        t.setText("");
    }
}
