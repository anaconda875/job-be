package com.example.job.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JobFilter {

    private Long jobCategoryId;
    private String location;
    private Long experienceLevelId;
    private String keyword;
    private int page = 0;
    private int pageSize = -1;

}
