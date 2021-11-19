package com.example.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<EarthquakeDataClass> {

    public DataAdapter(@NonNull Context context, ArrayList<EarthquakeDataClass> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.newlist, parent, false);
        }

        final EarthquakeDataClass currentData = getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag);
        TextView loc = (TextView) listItemView.findViewById(R.id.location);
        TextView date = (TextView) listItemView.findViewById(R.id.time);

        mag.setText(currentData.getmMagnitude());
        loc.setText(currentData.getmLocation());
        date.setText(currentData.getmDate());

        return listItemView;
    }
}
