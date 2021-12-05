package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataAdapter extends ArrayAdapter<EarthquakeDataClass> {

    private static final String LOCATION_SEPARATOR = " of ";

    public DataAdapter(@NonNull Context context, List<EarthquakeDataClass> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.newlist, parent, false);
        }

        EarthquakeDataClass currentData = getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag);
        TextView loc = (TextView) listItemView.findViewById(R.id.location);
        TextView loc2 = (TextView) listItemView.findViewById(R.id.location2);
        TextView date = (TextView) listItemView.findViewById(R.id.time);
        TextView time = (TextView) listItemView.findViewById(R.id.time2);

        String mag1= String.valueOf(currentData.getmMagnitude());
        mag.setText(mag1);

        //work with location
        String primaryLocation, locationOffset;
        String OriginalLocation = currentData.getmLocation();

        if(OriginalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = OriginalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;

            primaryLocation = parts[1];
        } else{
            locationOffset = "Near the ";
            primaryLocation = OriginalLocation;
        }
        loc.setText(locationOffset);
        loc2.setText(primaryLocation);

        //Create a new Date object from the time in milliseconds
        Date dateObject = new Date(currentData.getTimeInMiliSecond());

        //Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        String formattedDate = dateFormat.format(dateObject);
        //set date
        date.setText(formattedDate);

        //Now update the time
        //Return the formatted date string (i.e. "4:30 PM") from a Date object.

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateObject);
        time.setText(formattedTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(mag1);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(String magnitudeString) {
        Double magnitude = Double.parseDouble(magnitudeString);
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
