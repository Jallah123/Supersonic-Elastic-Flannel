package sidakejphes.nl.avans.edu.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import sidakejphes.nl.avans.edu.fragments.DetailFragment;
import sidakejphes.nl.avans.edu.models.Serie;
import sidakejphes.nl.avans.edu.wherewasi.DetailActivity;
import sidakejphes.nl.avans.edu.wherewasi.R;

public class SeriesAdapter extends AbstractSeriesAdapter {

    public SeriesAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.series_layout, parent, false);

            viewholder = new ViewHolder();
            viewholder.seriesName = (TextView) convertView.findViewById(R.id.seriesName);
            viewholder.seriesName.setTag(position);
            viewholder.firstAired = (TextView) convertView.findViewById(R.id.firstAired);
            viewholder.firstAired.setTag(position);

            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = (DetailFragment) ((Activity) getContext()).getFragmentManager().findFragmentById(R.id.detailFragment);
                if (fragment != null && fragment.isInLayout()) {
                    fragment.updateSerie(getSeries().get(position));
                } else {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("id", getSeries().get(position).getSeriesid());
                    intent.putExtra("name", getSeries().get(position).getSeriesName());
                    getContext().startActivity(intent);
                }
            }
        });
        Serie serie = getSeries().get(position);
        if (getSeries() != null) {
            viewholder.seriesName.setText(serie.getSeriesName());
            viewholder.firstAired.setText(serie.getFirstAired());
        }
        return convertView;
    }
}
