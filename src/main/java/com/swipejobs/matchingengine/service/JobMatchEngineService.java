package com.swipejobs.matchingengine.service;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.matcher.JobMatchEngine;
import com.swipejobs.matchingengine.model.Job;
import com.swipejobs.matchingengine.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobMatchEngineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchEngineService.class);

    @Autowired
    private JobGetService jobGetService;
    @Autowired
    private WorkerGetService workerGetService;
    @Autowired
    JobMatchEngine jobMatchEngine;

    /**
     * Use {@link com.swipejobs.matchingengine.matcher.JobMatchEngine} to find matching jobs
     *
     * @param workerId
     * @return A list of matching jobs
     */
    public List<Job> getMatchedJobs(int workerId) {

        List<Worker> workerList = workerGetService.getWorkers();
        List<Job> jobList = jobGetService.getJobs();
        List<Job> matchedJobs = new ArrayList<Job>();

        try {
            Worker worker = workerList.stream()
                    .filter(_worker -> _worker.getUserId() == workerId)
                    .findFirst()
                    .get();

            matchedJobs = jobMatchEngine.getMatchingJobs(worker, jobList);
            return matchedJobs;

        } catch (NoSuchElementException e) {
            LOGGER.error("Unable to find Worker with userId {} in the system", workerId);
            return matchedJobs;
        }
    }

}
