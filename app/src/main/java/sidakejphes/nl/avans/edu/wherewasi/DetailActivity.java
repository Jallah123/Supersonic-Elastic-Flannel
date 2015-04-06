package sidakejphes.nl.avans.edu.wherewasi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sidakejphes.nl.avans.edu.fragments.DetailFragment;
import sidakejphes.nl.avans.edu.models.Serie;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailActivity extends Activity {

    private Gson g = new Gson();
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("series", MODE_PRIVATE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //TODO pass information
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);

        DetailFragment df = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);

        Serie s = new Serie();
        s.setSeriesid(getIntent().getExtras().getString("id"));
        s.setSeriesName(getIntent().getExtras().getString("name"));
        df.updateSerie(s);
    }

    public void saveSerie(View v) {
        //set stringset
        Set<String> series = prefs.getStringSet("series", null);
        if (series == null) {
            series = new HashSet<String>();
        }
        String currentSerie = prefs.getString("currentSerie", "");
        if (!currentSerie.equals("")) {
            series.add(prefs.getString("currentSerie", ""));
        }
        prefs.edit().putStringSet("series", series).commit();
        CharSequence text = "Serie saved.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    public void deleteSerie(View v) {
        Set<String> series = prefs.getStringSet("series", null);
        String currentSerie = prefs.getString("currentSerie", "");

        if (!currentSerie.equals("")) {
            for (String s : series) {
                if (s.equals(currentSerie)) {
                    series.remove(s);
                }
            }
        }
        prefs.edit().putStringSet("series", series).commit();
        CharSequence text = "Serie removed.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
}
