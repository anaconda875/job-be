package com.example.job.controller;

import com.example.job.dto.request.JobRequest;
import com.example.job.entity.Job;
import com.example.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public Page<Job> searchJob(JobRequest jobRequest) {
        return jobService.searchJob(jobRequest);
    }

    @PostMapping
    public Job postJob(@RequestHeader("User-Id") Long employerId, @RequestBody Job job) {
        return jobService.postJob(employerId, job);
    }

}