package com.example.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quakereport.databinding.ActivityEarthquakeBinding;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    //URL
    private static final String Request_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    //Adapter for the list of earthquakes
    private DataAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        //Find a reference to the {@link ListView}
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //Create a new adapter that takes empty list of earthquake
        mAdapter = new DataAdapter(this, new ArrayList<EarthquakeDataClass>());

        //set the adapter on the lisview
        //so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        //set an item click listener on the listview
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Find the current earthquake that was clicked on
                EarthquakeDataClass currentEarthquake = mAdapter.getItem(i);

                //convert the string url into a URI Object to pass into the intent constructor
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                //create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                //send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        //Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(Request_URL);

    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthquakeDataClass>>{

        @Override
        protected List<EarthquakeDataClass> doInBackground(String... urls) {
            //Don't perform the request if there are no urls, or first one is null
            if(urls.length < 1 || urls[0] == null) return null;

            List<EarthquakeDataClass> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<EarthquakeDataClass> data) {
            //clear the adapter of previous earthquake data
            mAdapter.clear();

            //If there is a valid list of earthquakes then add them to the adapter's data set
            if(data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }
    }

}