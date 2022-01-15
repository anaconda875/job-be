package com.example.job.controller;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.Job;
import com.example.job.entity.JobCategory;
import com.example.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

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
    public Job create(@RequestParam("User-Id") Long employerId, @RequestBody Job job) {
        return jobService.create(employerId, job);
    }

    @PutMapping("/{id}")
    public Job update(@PathVariable Long id, @RequestBody Job job) {
        return jobService.update(id, job);
    }

    @PostMapping("/count-by-job-category")
    public int countByJobCategory(@RequestBody JobCategory jobCategory) {
        return jobService.countByJobCategory(jobCategory);
    }

    @GetMapping("/count-by-dates")
    public Map<LocalDate, Long> countByDates(LocalDate from, LocalDate to) {
        return jobService.countByDates(from, to);
    }

    @GetMapping("/count-applied-by-dates")
    public Map<LocalDate, Long> countAppliedByDates(LocalDate from, LocalDate to) {
        return jobService.countAppliedByDates(from, to);
    }

}
