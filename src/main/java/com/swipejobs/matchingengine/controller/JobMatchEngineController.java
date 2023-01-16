package com.swipejobs.matchingengine.controller;

import com.swipejobs.matchingengine.dto.JobDto;
import com.swipejobs.matchingengine.service.JobMatchEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobMatchEngineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchEngineController.class);
    @Autowired
    private JobMatchEngineService jobMatchEngineService;

    /**
     * This API takes a workerId and return a list of up to 3 matching jobs for that worker
     *
     * @param workerId
     * @return List of matching jobs
     */
    @RequestMapping(value = "/matching-engine/{workerId}", method = RequestMethod.GET)
    public List<JobDto> getMatchedJobs(@PathVariable String workerId) {
        try {
            LOGGER.info("Received job matching request for workerId = {}", workerId);
            return jobMatchEngineService.getMatchedJobs(workerId);
        } catch (Exception e) {
            LOGGER.error("Exception caught while trying to use the Match Engine", e);
            return new ArrayList<>();
        }
    }
}
