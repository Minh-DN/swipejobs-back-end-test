package com.swipejobs.matchingengine.model;

public class Location {

    private String longitude;
    private String latitude;

    public Location(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
