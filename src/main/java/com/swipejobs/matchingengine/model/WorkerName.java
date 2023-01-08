package com.swipejobs.matchingengine.model;

public class WorkerName {

    private String last;
    private String first;

    public WorkerName(String last, String first) {
        this.last = last;
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public String getFirst() {
        return first;
    }
}
