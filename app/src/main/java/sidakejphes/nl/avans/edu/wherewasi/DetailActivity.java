package sidakejphes.nl.avans.edu.wherewasi;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import sidakejphes.nl.avans.edu.fragments.DetailFragment;
import sidakejphes.nl.avans.edu.models.Serie;

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

        DetailFragment df = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);

        Serie s = new Serie();
        s.setSeriesid(getIntent().getExtras().getString("id"));
        s.setSeriesName(getIntent().getExtras().getString("name"));
        df.updateSerie(s);
    }
}
