package sidakejphes.nl.avans.edu.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;

import sidakejphes.nl.avans.edu.models.Season;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.parsers.SeriesParser;
import sidakejphes.nl.avans.edu.wherewasi.DataProvider;
import sidakejphes.nl.avans.edu.wherewasi.DetailActivity;
import sidakejphes.nl.avans.edu.wherewasi.IResultHandler;
import sidakejphes.nl.avans.edu.wherewasi.MainActivity;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
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

            DataProvider.doGet(DataProvider.API_KEY + "/series/" + serie.getSeriesid() + "/all", new IResultHandler() {
                @Override
                public void onSuccess(InputStream result) {
                    ArrayList<Serie> series = SeriesParser.parse(result);
                    if(series.size() != 1)
                        return;
                    final Serie serie = series.get(0);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView amountOfSeasons = (TextView) getView().findViewById(R.id.seasons_amount);
                            amountOfSeasons.setText("Amount of seasons : " + String.valueOf(serie.getSeasons().size()));
                            TextView amountOfEpisodes = (TextView) getView().findViewById(R.id.episodes_amount);
                            int amount = 0;
                            for(Season s : serie.getSeasons()){
                                amount += s.getEpisodes().size();
                            }
                            amountOfEpisodes.setText("Total episodes : " + amount);
                            TextView releaseDate = (TextView) getView().findViewById(R.id.release_date);
                            releaseDate.setText("First aired : " + serie.getFirstAired());
                            TextView overview = (TextView) getView().findViewById(R.id.overview);
                            overview.setText("Summary : " + serie.getOverview());
                            TextView rating = (TextView) getView().findViewById(R.id.rating);
                            rating.setText("Rating : " + serie.getRating() + "/10");
                            TextView status = (TextView) getView().findViewById(R.id.status);
                            status.setText("Status : " + serie.getStatus());
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
    }

    public void clearView(){
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
