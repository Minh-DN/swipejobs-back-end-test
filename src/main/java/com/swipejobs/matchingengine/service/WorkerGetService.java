package com.swipejobs.matchingengine.service;

import com.swipejobs.matchingengine.config.Configuration;
import com.swipejobs.matchingengine.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class WorkerGetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerGetService.class);
    @Autowired
    private Configuration configs;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Returns the list of workers from the Workers endpoint
     *
     * @return a list of workers
     */
    public List<Worker> getWorkers() {
        try {
//            ResponseEntity<Worker[]> workerResponse = restTemplate.getForEntity(configs.getWorkersResourceUrl(), Worker[].class);
//
//            Worker[] workers = workerResponse.getBody();
//
//            if (workers == null || workers.length == 0) {
//                LOGGER.error("Error: response from Workers endpoint is null or empty");
//                return null;
//            } else if (workerResponse.getStatusCode() != HttpStatus.OK) {
//                LOGGER.error("Unsuccessful response from Workers endpoint, Status Code: {}", workerResponse.getStatusCode());
//                return null;
//            }

            ResponseEntity<List<Worker>> workerResponse = restTemplate.exchange(configs.getWorkersResourceUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Worker>>() {
                });

            LOGGER.info("{}", workerResponse);

            return workerResponse.getBody();
        } catch (Exception e) {
            LOGGER.error("Exception caught while trying to get response from Workers endpoint", e);
            return null;
        }
    }

}
