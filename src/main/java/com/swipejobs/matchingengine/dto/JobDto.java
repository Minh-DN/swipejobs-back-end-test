package com.swipejobs.matchingengine.dto;

import com.swipejobs.matchingengine.model.Location;

public class JobDto {
    private String jobTitle;
    private String company;
    private String about;
    private Location location;

    public JobDto() {}

    public JobDto(String jobTitle, String company, String about, Location location) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.about = about;
        this.location = location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getAbout() {
        return about;
    }

    public Location getLocation() {
        return location;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
