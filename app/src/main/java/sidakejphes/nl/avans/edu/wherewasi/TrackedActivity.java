package sidakejphes.nl.avans.edu.wherewasi;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
    private EditText search;
    private ArrayList<Serie> series;
    private ArrayList<Serie> originalSeries;
    private TrackedSeriesAdapter adapter = TrackedSeriesAdapter.getInstance(this);
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked);
        prefs = getSharedPreferences("series", MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.trackedSeries);
        setTitle("Tracked Series");
        fillList();
        listView.setAdapter(adapter);
        adapter.setSeries(series);
        search = (EditText) findViewById(R.id.searchText);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateListView(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateListView(String s){
        series = (ArrayList<Serie>) originalSeries.clone();
        for(int i=0;i<series.size();i++){
            if(!series.get(i).getSeriesName().toLowerCase().contains(s.toLowerCase())){
                series.remove(i);
            }
        }
        adapter.setSeries(series);
        adapter.notifyDataSetChanged();
    }

    private void fillList() {
        Set<String> seriesSet = prefs.getStringSet("series", null);
        series = new ArrayList<Serie>();
        if (seriesSet != null) {
            for (String s : seriesSet) {
                series.add(g.fromJson(s, Serie.class));
            }
        }
        originalSeries = (ArrayList<Serie>) series.clone();
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
