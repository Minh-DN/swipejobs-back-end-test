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

    public Worker() {

    }

    public Worker(int rating, boolean isActive, Set<String> certificates, JobSearchAddress jobSearchAddress, String transportation, boolean hasDriversLicense, Set<WorkerAvailability> availability, String phone, String email, WorkerName name, int age, String guid, int userId) {
        this.rating = rating;
        this.isActive = isActive;
        this.certificates = certificates;
        this.jobSearchAddress = jobSearchAddress;
        this.transportation = transportation;
        this.hasDriversLicense = hasDriversLicense;
        this.availability = availability;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.age = age;
        this.guid = guid;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Worker{" +
                "rating=" + rating +
                ", isActive=" + isActive +
                ", certificates=" + certificates +
                ", jobSearchAddress=" + jobSearchAddress +
                ", transportation='" + transportation + '\'' +
                ", hasDriversLicense=" + hasDriversLicense +
                ", availability=" + availability +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                ", age=" + age +
                ", guid='" + guid + '\'' +
                ", userId=" + userId +
                '}';
    }
}
