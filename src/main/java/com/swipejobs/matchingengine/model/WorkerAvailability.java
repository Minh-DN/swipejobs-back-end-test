package com.swipejobs.matchingengine.model;

public class WorkerAvailability {

    private String title;
    private int dayIndex;

    public WorkerAvailability() {
    }

    public WorkerAvailability(String title, int dayIndex) {
        this.title = title;
        this.dayIndex = dayIndex;
    }

    public String getTitle() {
        return title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    @Override
    public String toString() {
        return "WorkerAvailability{" +
                "title='" + title + '\'' +
                ", dayIndex=" + dayIndex +
                '}';
    }
}
