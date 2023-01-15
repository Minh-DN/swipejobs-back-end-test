package com.swipejobs.matchingengine.matcher;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.model.Job;
import com.swipejobs.matchingengine.model.Worker;
import com.swipejobs.matchingengine.util.JobMatchEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMatchEngine {
    @Autowired
    private Configuration configs;
    @Autowired
    private JobMatchEngineUtils utils;

    /**
     * Matches jobs to a worker by filtering the available jobs by:
     * 1. Driver License Requirement
     * 2. Location (find jobs within worker's maximum distance)
     * 3. Certificates Requirements
     *
     * @param worker  worker to find matching jobs for
     * @param jobList list of available jobs (from Jobs endpoint)
     * @return A list of matching jobs
     */
    public List<Job> getMatchingJobs(Worker worker, List<Job> jobList) {

        List<Job> matchedJobs;

        matchedJobs = jobList.stream()
                .filter(job -> utils.filterByDriverLicense(job, worker)) // Step 1 - filter by driver license requirement
                .filter(job -> utils.compareDistance(job, worker)) // Step 2 - filter by location
                .filter(job -> utils.filterByCertificates(job, worker)) // Step 3 - filter by certificate requirements
                .collect(Collectors.toList());

        // TODO: implement a check to add a warning if job's start date
        //  does not match with worker's availability

        // if found more than 3 matches, trim result to closest 3 jobs
        if (matchedJobs.size() > configs.getMaxJobMatches()) {
            return utils.getClosestJobs(matchedJobs, worker);
        }

        return matchedJobs;
    }

}
