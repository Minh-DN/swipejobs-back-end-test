package com.swipejobs.matchingengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MatchingEngineApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchingEngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MatchingEngineApplication.class, args);
		LOGGER.info("Matching Engine now online");
	}

}
