package com.swipejobs.matchingengine.service;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class WorkerGetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerGetService.class);
    @Autowired
    private Configuration configs;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Returns the list of workers from the Workers endpoint
     *
     * @return a list of workers
     */
    public List<Worker> getWorkers() {
        try {
            ResponseEntity<Worker[]> workerResponse = restTemplate.getForEntity(configs.getWorkersResourceUrl(), Worker[].class);

            Worker[] workers = workerResponse.getBody();

            if (workers == null || workers.length == 0) {
                LOGGER.error("Error: response from Workers endpoint is null or empty");
                return null;
            } else if (workerResponse.getStatusCode() != HttpStatus.OK) {
                LOGGER.error("Unsuccessful response from Workers endpoint, Status Code: {}", workerResponse.getStatusCode());
                return null;
            }

            return Arrays.asList(workers);
        } catch (Exception e) {
            LOGGER.error("Exception caught while trying to get response from Workers endpoint", e);
            return null;
        }
    }

}
