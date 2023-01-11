package com.swipejobs.matchingengine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
    @Value("${workersResourceUrl:https://test.swipejobs.com/api/workers}")
    private String workersResourceUrl;

    @Value("${jobsResourceUrl:https://test.swipejobs.com/api/jobs}")
    private String jobsResourceUrl;

    @Value("${maxJobMatches:3}")
    private int maxJobMatches;

    public String getWorkersResourceUrl() {
        return workersResourceUrl;
    }

    public String getJobsResourceUrl() {
        return jobsResourceUrl;
    }

    public int getMaxJobMatches() {
        return maxJobMatches;
    }
}
