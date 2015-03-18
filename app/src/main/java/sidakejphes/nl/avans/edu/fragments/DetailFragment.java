package sidakejphes.nl.avans.edu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailfragment_layout, container, false);
    }
    public void updateSerie(Serie serie){
        TextView t = (TextView) getView().findViewById(R.id.detail_seriesName);
        t.setText(serie.getSeriesName());
    }
}
