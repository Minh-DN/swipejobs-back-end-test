package com.swipejobs.matchingengine.util;

import com.swipejobs.matchingengine.model.Job;
import com.swipejobs.matchingengine.model.Worker;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JobMatchEngineUtils {
    /**
     * Calculate the distance between two co-ordinates using the Haversine method
     *
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return Distance in km
     */
    public double calcDistance(String latitude1, String longitude1,
                               String latitude2, String longitude2) {

        final int R = 6371; // Radius of the Earth (km)

        // Convert co-ordinates to double
        double lat1 = Double.parseDouble(latitude1);
        double lon1 = Double.parseDouble(longitude1);
        double lat2 = Double.parseDouble(latitude2);
        double lon2 = Double.parseDouble(longitude2);

        // Calculate distance using Haversine method
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }

    public boolean compareDistance(Job job, Worker worker) {
        // worker's location
        String workerLat = worker.getJobSearchAddress().getLatitude();
        String workerLong = worker.getJobSearchAddress().getLongitude();

        // job's location
        String jobLat = job.getLocation().getLatitude();
        String jobLong = job.getLocation().getLongitude();

        double distance = calcDistance(workerLat, workerLong, jobLat, jobLong);
        // worker's max distance from job search address
        double maxDistance = worker.getJobSearchAddress().getMaxJobDistance();

        return distance <= maxDistance;
    }

    public boolean filterByCertificates(Job job, Worker worker) {
        List<String> requiredCerts = job.getRequiredCertificates();
        Set<String> workerCerts = worker.getCertificates();

        for (String requiredCert : requiredCerts) {
            if (!workerCerts.contains(requiredCert)) {
                return false;
            }
        }
        return true;
    }

    public boolean filterByDriverLicense(Job job, Worker worker) {
        if (!job.isDriverLicenseRequired()) {
            return true;
        } else {
            return job.isDriverLicenseRequired() == worker.getHasDriversLicense();
        }
    }

    public List<Job> getClosestJobs(List<Job> jobList, Worker worker) {
        Function<Job, Double> getDistanceToWorker = (job) -> {
            String jobLat = job.getLocation().getLatitude();
            String jobLon = job.getLocation().getLongitude();

            String workerLat = worker.getJobSearchAddress().getLatitude();
            String workerLon = worker.getJobSearchAddress().getLongitude();

            return calcDistance(jobLat, jobLon, workerLat, workerLon);
        };

        // Sort the job list in order of distance to worker's job search address
        jobList.sort(Comparator.comparingDouble(getDistanceToWorker::apply));

        // return closest 3 jobs
        return jobList.stream().limit(3).collect(Collectors.toList());
    }
}
