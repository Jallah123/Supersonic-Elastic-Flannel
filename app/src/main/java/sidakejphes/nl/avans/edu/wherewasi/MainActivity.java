package sidakejphes.nl.avans.edu.wherewasi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;

import sidakejphes.nl.avans.edu.adapters.SeriesAdapter;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.parsers.SeriesParser;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Serie> series = new ArrayList<Serie>();
    private SeriesAdapter seriesAdapter = new SeriesAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.series);
        lv.setAdapter(seriesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void searchSeries(View v){
        EditText search = (EditText) findViewById(R.id.searchValue);
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("seriesname", search.getText().toString()));
        DataProvider.doGet("GetSeries.php", params, new IResultHandler() {
            @Override
            public void onSuccess(InputStream result) {
                try {
                    ArrayList<Serie> series = SeriesParser.parse(result);
                    seriesAdapter.setSeries(series);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seriesAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
