package com.example.quakereport;

public class EarthquakeDataClass {
    private String mMagnitude;
    private String mLocation;
    private long timeInMiliSecond;

    public EarthquakeDataClass(){
        mMagnitude = "0.0";
        mLocation = "Null";
        timeInMiliSecond = 0L;
    }

    public  EarthquakeDataClass(String mMagnitude, String mLocation, long timeInMiliSecond){
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.timeInMiliSecond = timeInMiliSecond;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getTimeInMiliSecond() {
        return timeInMiliSecond;
    }
}
