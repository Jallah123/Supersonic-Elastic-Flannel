package sidakejphes.nl.avans.edu.wherewasi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

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

    private Serie serie;
    private Gson g = new Gson();
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getPreferences(MODE_PRIVATE);
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
        serie = df.updateSerie(s);
    }

    public void saveSerie(View v) {
        //set stringset
        Set<String> series =  prefs.getStringSet("series", null);
        if(series == null){
            series = new HashSet<String>();
        }
        series.add(g.toJson(serie));
        prefs.edit().putStringSet("series", series).commit();

        //get & read stringset (move code to proper place)
        Set<String> set = prefs.getStringSet("series", null);
        if(set != null) {
            for (String s : set) {
                Serie serie = g.fromJson(s, Serie.class);
                serie = serie;
            }
        }
    }
}
