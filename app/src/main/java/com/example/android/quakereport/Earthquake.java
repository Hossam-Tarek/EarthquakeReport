package com.example.android.quakereport;

/**
 * Created by hossam on 3/17/18.
 */

public class Earthquake
{
    private String magnitude;
    private String location;
    private long timeInMilliseconds;

    public Earthquake(String magnitude, String location, long timeInMilliseconds) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }
}
