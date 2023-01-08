package com.swipejobs.matchingengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSearchAddress {

    private String unit;
    private int maxJobDistance;
    private String longitude;
    private String latitude;

    public JobSearchAddress(String unit, int maxJobDistance, String longitude, String latitude) {
        this.unit = unit;
        this.maxJobDistance = maxJobDistance;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getUnit() {
        return unit;
    }

    public int getMaxJobDistance() {
        return maxJobDistance;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
