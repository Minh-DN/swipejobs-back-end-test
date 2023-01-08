package com.swipejobs.matchingengine.matcher;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.model.Job;
import com.swipejobs.matchingengine.model.Worker;
import com.swipejobs.matchingengine.util.JobMatchEngineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobMatchEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchEngine.class);
    @Autowired
    private Configuration configs;
    @Autowired
    private JobMatchEngineUtils utils;

    /**
     * Matches jobs to a worker by filtering the available jobs by:
     *      1. Driver License Requirement
     *      2. Location (find jobs within worker's maximum distance)
     *      3. Certificates Requirements
     *
     * @param worker worker to find matching jobs for
     * @param jobList list of available jobs (from Jobs endpoint)
     * @return A list of matching jobs
     */
    public List<Job> getMatchingJobs(Worker worker, List<Job> jobList) {

        List<Job> matchedJobs = new ArrayList<Job>();

        // Step 1 - filter by driver license requirement
        matchedJobs = jobList.stream()
                .filter(job -> filterByDriverLicense(job, worker))
                .collect(Collectors.toList());

        // Step 2 - filter by location
        matchedJobs = filterByLocation(matchedJobs, worker);

        // Step 3 - filter by certificate requirements
        matchedJobs = filterByCertificate(matchedJobs, worker);

        // TODO: implement a check to add a warning if job's start date
        //  does not match with worker's availability

        // if found more than 3 matches, trim result to closest 3 jobs
        if (matchedJobs.size() > configs.getMaxJobMatches()) {
            return utils.getClosestJobs(matchedJobs, worker);
        }

        return matchedJobs;
    }

    private boolean filterByDriverLicense(Job job, Worker worker) {
        if (!job.isDriverLicenseRequired()) {
            return false;
        } else {
            return job.isDriverLicenseRequired() == worker.getHasDriversLicense();
        }
    }

    private List<Job> filterByLocation(List<Job> jobList, Worker worker) {
        jobList = jobList.stream()
                .filter(job -> utils.compareDistance(job, worker))
                .collect(Collectors.toList());
        return jobList;
    }

    private List<Job> filterByCertificate(List<Job> jobList, Worker worker) {
        jobList = jobList.stream()
                .filter(job -> utils.filterByCertificates(job, worker))
                .collect(Collectors.toList());
        return jobList;
    }

}
