package sidakejphes.nl.avans.edu.wherewasi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jelle on 18-3-2015.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //TODO pass information
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        TextView t = (TextView) findViewById(R.id.detail_seriesName);
        Intent intent = getIntent();
        String name = (String) intent.getExtras().get("name");
        t.setText(name);
    }
}
