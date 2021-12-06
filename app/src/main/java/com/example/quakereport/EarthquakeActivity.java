package com.example.quakereport;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.content.AsyncTaskLoader;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeDataClass>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    //URL
    private static final String Request_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    //Adapter for the list of earthquakes
    private DataAdapter mAdapter;

    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        //Find a reference to the {@link ListView}
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        //emptyview
        mEmptyTextView = (TextView) findViewById(R.id.EmptyText);
        earthquakeListView.setEmptyView(mEmptyTextView);

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

        // Get a reference to the LoaderManager, in order to interact with loaders.
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        LoaderManager.getInstance(this).initLoader(EARTHQUAKE_LOADER_ID, null, this);

    }

    @NonNull
    @Override
    public Loader<List<EarthquakeDataClass>> onCreateLoader(int id, @Nullable Bundle args) {
        //Create new loader for the given url
        return new EarthquakeLoader(this, Request_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<EarthquakeDataClass>> loader, List<EarthquakeDataClass> data) {
        //Hide loading Indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        
        //clear the adapter of previous earthquake data
        mAdapter.clear();

        //If there is a valid list of earthquakes then add them to the adapter's data set
        if(data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        }

        //check if network was connected or not
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork == null){

            //state that there is no internet connection
            mEmptyTextView.setText("No Internet Connection found :( ");

        } else if(activeNetwork != null && activeNetwork.isConnected()){

            //There is internet but the list is still empty
            mEmptyTextView.setText("No earthquake data found :( ");

        }
        else{

            //Set empty state text to display
           mEmptyTextView.setText("No earthquake data found :( ");

        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<EarthquakeDataClass>> loader) {
        //Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }

}