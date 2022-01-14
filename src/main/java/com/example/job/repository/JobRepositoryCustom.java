package com.example.job.repository;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepositoryCustom {

    Page<Job> find(Pageable pageable, JobFilter jobFilter);

}
