package sidakejphes.nl.avans.edu.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import sidakejphes.nl.avans.edu.fragments.DetailFragment;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.wherewasi.DetailActivity;
import sidakejphes.nl.avans.edu.wherewasi.R;

/**
 * Created by Jelle on 18-3-2015.
 */
public class SeriesAdapter extends BaseAdapter {
    private ArrayList<Serie> series;
    private Context context;

    public SeriesAdapter(Context context){
        this.series  = new ArrayList<Serie>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return series != null ? series.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return series != null ? series.get(position) : null;
    }

    public Object getBySeriesName(String name){
        for(Serie serie: series) {
            if(serie.getSeriesName().equals(name)) {
                return serie;
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.series_layout, parent, false);

            viewholder = new ViewHolder();
            viewholder.seriesName = (TextView) convertView.findViewById(R.id.seriesName);
            viewholder.seriesName.setTag(position);
            viewholder.firstAired = (TextView) convertView.findViewById(R.id.firstAired);
            viewholder.firstAired.setTag(position);

            viewholder.seriesName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailFragment fragment = (DetailFragment) ((Activity) context).getFragmentManager().findFragmentById(R.id.detailFragment);
                    if(fragment != null && fragment.isInLayout()){
                        fragment.updateSerie(series.get(position));
                    }else {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("id", series.get(position).getSeriesid());
                        intent.putExtra("name", series.get(position).getSeriesName());
                        context.startActivity(intent);
                    }
                }
            });
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Serie serie= series.get(position);
        if(series != null) {
            viewholder.seriesName.setText(serie.getSeriesName());
            viewholder.firstAired.setText(serie.getFirstAired());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView seriesName;
        TextView firstAired;
    }

    public Context getContext(){
        return context;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
