package com.swipejobs.matchingengine.service;

import com.swipejobs.matchingengine.dto.JobDto;
import com.swipejobs.matchingengine.mapper.JobMapper;
import com.swipejobs.matchingengine.matcher.JobMatchEngine;
import com.swipejobs.matchingengine.model.Job;
import com.swipejobs.matchingengine.model.Worker;
import com.swipejobs.matchingengine.util.JobMatchEngineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobMatchEngineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchEngineService.class);

    @Autowired
    private JobGetService jobGetService;
    @Autowired
    private WorkerGetService workerGetService;
    @Autowired
    private JobMatchEngineUtils utils;
    @Autowired
    private JobMatchEngine jobMatchEngine;
    @Autowired
    private JobMapper jobMapper;

    /**
     * Use {@link com.swipejobs.matchingengine.matcher.JobMatchEngine} to find matching jobs
     *
     * @param _workerId
     * @return A list of matching jobs
     */
    public List<JobDto> getMatchedJobs(String _workerId) {

        if (!utils.isNumeric(_workerId)) {
            LOGGER.error("Invalid workerId received: {}", _workerId);
            return new ArrayList<>();
        }

        int workerId = Integer.parseInt(_workerId);

        List<Worker> workerList = workerGetService.getWorkers();
        List<Job> jobList = jobGetService.getJobs();
        List<Job> matchedJobs;


        Worker worker = workerList.stream()
                .filter(_worker -> _worker.getUserId() == workerId)
                .findFirst()
                .orElse(null);

        if (worker == null) {
            LOGGER.error("Unable to find Worker with userId {} in the system", workerId);
            return new ArrayList<>();
        }

        matchedJobs = jobMatchEngine.getMatchingJobs(worker, jobList);
        List<JobDto> matchedJobsDto = matchedJobs.stream()
                .map(e -> jobMapper.toDto(e))
                .toList();

        return matchedJobsDto;
    }

}
