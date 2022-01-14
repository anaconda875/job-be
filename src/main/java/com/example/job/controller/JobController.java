package com.example.job.controller;

import com.example.job.dto.request.JobRequest;
import com.example.job.entity.Job;
import com.example.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public Page<Job> searchJob(JobRequest jobRequest) {
        return jobService.searchJob(jobRequest);
    }

}
