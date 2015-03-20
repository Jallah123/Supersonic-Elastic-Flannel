package sidakejphes.nl.avans.edu.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.services.DownloadService;
import sidakejphes.nl.avans.edu.wherewasi.DetailActivity;
import sidakejphes.nl.avans.edu.wherewasi.MainActivity;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailFragment extends Fragment {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            if(bundle != null) {
                String string = bundle.getString(DownloadService.FILEPATH);
                int resultcode = bundle.getInt(DownloadService.RESULT);
                if(resultcode == Activity.RESULT_OK) {
                    Toast.makeText(getActivity(), "Download uri:" + string, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Download failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter(DownloadService.NOTIFICATION));
    }
    @Override
    public void onPause(){
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("DetailFragment", "Created fragment");
        return inflater.inflate(R.layout.detailfragment_layout, container, false);
    }
    public void updateSerie(Serie serie){
        TextView t = (TextView) getView().findViewById(R.id.detail_seriesName);
        t.setText(serie.getSeriesName());


        Intent intent = new Intent(this.getActivity(), DownloadService.class);
        intent.putExtra(DownloadService.FILENAME, serie.getSeriesid() + ".xml");
        intent.putExtra(DownloadService.URL, "/series/" + serie.getSeriesid() + "/all");
        getActivity().startService(intent);
    }
}
