package sidakejphes.nl.avans.edu.wherewasi;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import sidakejphes.nl.avans.edu.adapters.TrackedSeriesAdapter;
import sidakejphes.nl.avans.edu.fragments.TrackedDetailFragment;
import sidakejphes.nl.avans.edu.models.Serie;

public class TrackedDetailActivity extends ActionBarActivity {

    private SharedPreferences prefs;
    private Serie serie;
    private Gson g = new Gson();
    private TrackedSeriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_detail);
        prefs = getSharedPreferences("series", MODE_PRIVATE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //TODO pass information
            finish();
            return;
        }

        TrackedDetailFragment df = (TrackedDetailFragment) getFragmentManager().findFragmentById(R.id.trackedDetailFragment);

        serie = new Serie();
        serie.setSeriesid(getIntent().getExtras().getString("id"));
        adapter = TrackedSeriesAdapter.getInstance(getApplicationContext());
        df.updateSerie(serie);
    }

    public void deleteSerie(View v) {
        Set<String> series = prefs.getStringSet("series", null);
        if (!serie.equals("")) {
            for (String s : series) {
                Serie tempSerie = g.fromJson(s, Serie.class);
                if (tempSerie.getSeriesid().equals(serie.getSeriesid())) {
                    series.remove(s);
                    break;
                }
            }
        }
        prefs.edit().putStringSet("series", series).commit();
        CharSequence text = "Serie removed.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
        ArrayList<Serie> seriesArraylist = new ArrayList<Serie>();
        for(String s: series){
            seriesArraylist.add(g.fromJson(s, Serie.class));
        }
        adapter.setSeries(seriesArraylist);
        adapter.notifyDataSetChanged();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tracked_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
