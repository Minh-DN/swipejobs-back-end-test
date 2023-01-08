package com.swipejobs.matchingengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {

    private boolean driverLicenseRequired;
    private List<String> requiredCertificates;
    private Location location;
    private String billRate;
    private int workersRequired;
    private Date startDate;
    private String about;
    private String jobTitle;
    private String company;
    private String guid;
    private int jobId;

    public boolean isDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    public List<String> getRequiredCertificates() {
        return requiredCertificates;
    }

    public Location getLocation() {
        return location;
    }

    public String getBillRate() {
        return billRate;
    }

    public int getWorkersRequired() {
        return workersRequired;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getAbout() {
        return about;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getGuid() {
        return guid;
    }

    public int getJobId() {
        return jobId;
    }
}
