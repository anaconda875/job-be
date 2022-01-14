package com.example.job.controller;

import com.example.job.entity.Job;
import com.example.job.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}/jobs")
    public Set<Job> findApplied(@PathVariable("id") Long employeeId) {
        return employeeService.findApplied(employeeId);
    }

    @PostMapping("/{id}/jobs/{jid}/apply")
    public void applyJob(@PathVariable("id") Long employeeId, @PathVariable("jid") Long jobId) {
        employeeService.applyJob(employeeId, jobId);
    }

}
