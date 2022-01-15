package com.example.job.dto.request;

import lombok.Data;

@Data
public class JobFilter {

    private Long jobCategoryId;
    private String location;
    private Long experienceLevelId;
    private String keyword;
    private int page = 0;
    private int pageSize = -1;

}
