package sidakejphes.nl.avans.edu.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import sidakejphes.nl.avans.edu.models.Serie;

public abstract class AbstractSeriesAdapter extends BaseAdapter {
    private ArrayList<Serie> series;
    private Context context;

    public AbstractSeriesAdapter(Context context){
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
     return null;
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
