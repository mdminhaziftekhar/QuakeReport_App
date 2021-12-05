package com.example.quakereport;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeDataClass>> {
    //Tag for log messages
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    //Query URL
    private String mUrl;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<EarthquakeDataClass> loadInBackground() {
        //Don't perform the request if there are no urls, or first one is null
        if(mUrl == null) return null;

        List<EarthquakeDataClass> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }

}

