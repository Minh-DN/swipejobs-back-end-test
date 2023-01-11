# swipejobs back-end test

A simple matching engine that presents Workers with appropriate jobs

## Matching Engine's Algorithm
This application matches jobs to a worker by filtering the available jobs as follows:

Step 1: Filter by driver's license requirement
* If the Worker doesn't have a driver's license, filter out jobs that require having a driver's license

Step 2: Filter by location

* Filter out jobs whose distance to the Worker's job search address is greater than the Worker's max job distance
* Distances are calculated using the Haversine formula

Step 3: Filter by certificate requirements
* Filter out jobs that require certificates that the Worker does not have

Step 4 (if still more than 3 matches): Trim the matches list to the closest 3 jobs
* Sort the matches list by distance to Worker's job search address, in ascending order
* Return the first 3 matches in the list

## API Documentation
### GET /matching-engine/{workerId}

## Installation Guide
**Ensure you have Java and Maven installed on your machine**
1. Open terminal / command prompt in your desired folder
2. Run the following command
```
git clone git@github.com:Minh-DN/swipejobs-back-end-test.git
```
3. Navigate into the project's root folder
```
cd swipejobs-back-end-test
```
4. Run the following command
```
mvn clean install
java -jar target/matching-engine-0.0.1-SNAPSHOT.jar 
```
