package com.example.job.controller;

import com.example.job.entity.Employer;
import com.example.job.entity.Job;
import com.example.job.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping
    public Page<Employer> find(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        return employerService.find(page, pageSize);
    }

    @GetMapping("/{id}/jobs")
    public Set<Job> findJobs(@PathVariable("id") Long id) {
        return employerService.findJobs(id);
    }

    @PutMapping("/{id}")
    public Employer update(@PathVariable("id") Long id, @RequestBody Employer employer) {
        return employerService.update(id, employer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        employerService.delete(id);
    }

}
