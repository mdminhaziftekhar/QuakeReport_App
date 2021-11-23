package com.example.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        TextView loc2 = (TextView) listItemView.findViewById(R.id.location2);
        TextView date = (TextView) listItemView.findViewById(R.id.time);
        TextView time = (TextView) listItemView.findViewById(R.id.time2);

        mag.setText(currentData.getmMagnitude());

        //work with location
        String s = currentData.getmLocation();
        String location1="", location2="";
        if(s.contains("of") == true){
            int i = s.indexOf("of");
            int j = 0;
            for(j = 0; j<i+2; j++){
                location1+=s.charAt(j);
            }
            for(j=i+2; j<s.length(); j++){
                location2+=s.charAt(j);
            }
        }else{
            location1+="Near the";
            location2+=s;
        }

        //now set text in textview of locations
        loc.setText(location1);
        loc2.setText(location2);

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

        return listItemView;
    }
}
