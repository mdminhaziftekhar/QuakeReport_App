package com.example.quakereport;

public class EarthquakeDataClass {
    private String mMagnitude;
    private String mLocation;
    private String mDate;

    public EarthquakeDataClass(){
        mMagnitude = "0.0";
        mLocation = "Null";
        mDate = "Feb 0, 0000";
    }

    public  EarthquakeDataClass(String mMagnitude, String mLocation, String mDate){
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }
}
