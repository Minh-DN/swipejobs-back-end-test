package com.swipejobs.matchingengine.service;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class JobGetService {

    public static final Logger LOGGER = LoggerFactory.getLogger(JobGetService.class);
    @Autowired
    private Configuration configs;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Job> getJobs() {
        try {
            ResponseEntity<Job[]> jobResponse = restTemplate.getForEntity(configs.getJobsResourceUrl(), Job[].class);

            if (jobResponse.getBody() == null || jobResponse.getBody().length == 0) {
                LOGGER.error("Error: response from Jobs endpoint is null or empty");
                return null;
            } else if (jobResponse.getStatusCode() != HttpStatus.OK) {
                LOGGER.error("Unsuccessful response from Jobs endpoint. Status Code: {}", jobResponse.getStatusCode());
                return null;
            }
            return Arrays.asList(jobResponse.getBody());

        } catch (Exception e) {
            LOGGER.error("Exception caught while trying to get response from Jobs endpoint", e);
            return null;
        }
    }
}
