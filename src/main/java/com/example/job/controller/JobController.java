package com.example.job.controller;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.Job;
import com.example.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public Page<Job> searchJob(JobFilter jobFilter) {
        return jobService.find(jobFilter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job create(@RequestHeader("User-Id") Long employerId, @RequestBody Job job) {
        return jobService.create(employerId, job);
    }

    @PutMapping("/{id}")
    public Job update(@PathVariable Long id, @RequestBody Job job) {
        return jobService.update(id, job);
    }

}
