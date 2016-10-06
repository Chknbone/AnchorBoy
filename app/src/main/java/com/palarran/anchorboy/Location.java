package com.palarran.anchorboy;

/**
 * Created by jason on 10/6/16.
 */

public class Location {
    private final String latitude;
    private final String longitude;

    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
