package sidakejphes.nl.avans.edu.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import sidakejphes.nl.avans.edu.adapters.SeriesAdapter;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 25-3-2015.
 */
public class TrackFragment extends Fragment {

    Gson g = new Gson();
    SeriesAdapter seriesAdapter = new SeriesAdapter(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillListView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trackfragment_layout, container, false);
    }

    public void fillListView() {
        ListView listView = (ListView) getActivity().findViewById(R.id.series);
        Set<String> jsonSeries = getActivity().getPreferences(Activity.MODE_PRIVATE).getStringSet("series", null);
        if (jsonSeries != null) {
            ArrayList<Serie> series = new ArrayList<Serie>();
            for (String s : jsonSeries) {
                series.add(g.fromJson(s, Serie.class));
            }

            // ArrayAdapter<Serie> seriesAdapter = new ArrayAdapter<Serie>(getActivity(), R.layout.series_layout, series);
            listView.setAdapter(seriesAdapter);
            seriesAdapter.setSeries(series);
            seriesAdapter.notifyDataSetChanged();
        }
        //TODO no results
    }

}
