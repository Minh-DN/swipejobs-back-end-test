package com.swipejobs.matchingengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {

    private int rating;
    private boolean isActive;
    private Set<String> certificates;
    private JobSearchAddress jobSearchAddress;
    private String transportation;
    private boolean hasDriversLicense;
    private Set<WorkerAvailability> availability;
    private String phone;
    private String email;
    private WorkerName name;
    private int age;
    private String guid;
    private int userId;

    public Worker(int workerId) {
        this.userId = workerId;
    }

    public int getRating() {
        return rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public Set<String> getCertificates() {
        return certificates;
    }

    public JobSearchAddress getJobSearchAddress() {
        return jobSearchAddress;
    }

    public String getTransportation() {
        return transportation;
    }

    public boolean getHasDriversLicense() {
        return hasDriversLicense;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public Set<WorkerAvailability> getAvailability() {
        return availability;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public WorkerName getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGuid() {
        return guid;
    }

    public int getUserId() {
        return userId;
    }

}
