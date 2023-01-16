package com.swipejobs.matchingengine.mapper;

import com.swipejobs.matchingengine.dto.JobDto;
import com.swipejobs.matchingengine.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobDto toDto(Job entity) {
        JobDto dto = new JobDto();
        dto.setJobTitle(entity.getJobTitle());
        dto.setCompany(entity.getCompany());
        dto.setAbout(entity.getAbout());
        dto.setLocation(entity.getLocation());

        return dto;
    }
}
