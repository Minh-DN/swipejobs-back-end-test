package com.swipejobs.matchingengine;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class includes two simple test cases:
 * 1. Test that the application returns an empty array response when given an invalid worker ID
 * 2. Test that the application returns the correct response for worker with ID 3
 *
 * This test class can be improved by constructing the expected response instead of hard-coding it
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatchingEngineApplicationTests {

	@Autowired
	private MockMvc mvc;
	private final int nonExistingWorkerIdToTest = 51;
	private final int existingWorkerIdToTest = 3;
	private final String expectedResponse = "[{\"driverLicenseRequired\":false,\"requiredCertificates\":[\"The Encouraging Word Award\"],\"location\":{\"longitude\":\"14.987061\",\"latitude\":\"50.212725\"},\"billRate\":\"$14.79\",\"workersRequired\":4,\"startDate\":\"2015-11-14T10:07:21.887+00:00\",\"about\":\"Magna commodo velit dolor aliquip exercitation. Esse irure duis eu duis veniam ea minim ex. Aliqua deserunt dolore officia do.\",\"jobTitle\":\"Director of First Impressions\",\"company\":\"Nimon\",\"guid\":\"562f66aaf3cb186fc0776de9\",\"jobId\":27}]";
	@Test
	void getMatchedJobs_NonExistingWorker_EmptyResponse() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/matching-engine/" + nonExistingWorkerIdToTest)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
	@Test
	void getMatchingJobs_ForExistingWorker() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/matching-engine/" + existingWorkerIdToTest)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedResponse));
	}


}
