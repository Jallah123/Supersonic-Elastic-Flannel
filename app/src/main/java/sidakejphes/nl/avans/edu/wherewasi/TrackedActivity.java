package sidakejphes.nl.avans.edu.wherewasi;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import sidakejphes.nl.avans.edu.adapters.AbstractSeriesAdapter;
import sidakejphes.nl.avans.edu.adapters.SeriesAdapter;
import sidakejphes.nl.avans.edu.adapters.TrackedSeriesAdapter;
import sidakejphes.nl.avans.edu.models.Serie;

public class TrackedActivity extends ActionBarActivity {

    private SharedPreferences prefs;
    private Gson g = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked);
        prefs = getSharedPreferences("series", MODE_PRIVATE);
        setTitle("Tracked Series");
        fillList();
    }

    private void fillList() {
        ListView listView = (ListView) findViewById(R.id.trackedSeries);
        TrackedSeriesAdapter adapter = TrackedSeriesAdapter.getInstance(this);
        Set<String> seriesSet = prefs.getStringSet("series", null);
        ArrayList<Serie> series = new ArrayList<Serie>();
        if (seriesSet != null) {
            for (String s : seriesSet) {
                series.add(g.fromJson(s, Serie.class));
            }
        }
        listView.setAdapter(adapter);
        adapter.setSeries(series);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tracked, menu);
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
