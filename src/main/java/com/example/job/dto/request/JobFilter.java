package com.example.job.dto.request;

import com.example.job.entity.ExperienceLevel;
import com.example.job.entity.JobCategory;
import lombok.Data;

@Data
public class JobFilter {

    private JobCategory jobCategory;
    private String location;
    private ExperienceLevel experienceLevel;
    private String keyword;
    private int page = 0;
    private int pageSize = -1;

}
