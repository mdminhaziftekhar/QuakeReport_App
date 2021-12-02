package com.example.quakereport;

import androidx.core.content.ContextCompat;

public class EarthquakeDataClass {
    private double mMagnitude;
    private String mLocation;
    private long timeInMiliSecond;
    private String url;


    public EarthquakeDataClass(){
        mMagnitude = 0.0;
        mLocation = "Null";
        timeInMiliSecond = 0L;
        url = "null";
    }

    public  EarthquakeDataClass(double mMagnitude, String mLocation, long timeInMiliSecond, String url){
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.timeInMiliSecond = timeInMiliSecond;
        this.url = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getTimeInMiliSecond() {
        return timeInMiliSecond;
    }

    public String getUrl() {
        return url;
    }
}
