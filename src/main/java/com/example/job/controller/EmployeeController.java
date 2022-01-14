package com.example.job.controller;

import com.example.job.entity.Employee;
import com.example.job.entity.Job;
import com.example.job.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Page<Employee> find(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        return employeeService.find(page, pageSize);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @GetMapping("/{id}/jobs")
    public Set<Job> findApplied(@PathVariable("id") Long employeeId) {
        return employeeService.findApplied(employeeId);
    }

    @PostMapping("/{id}/jobs/{jid}/apply")
    public void applyJob(@PathVariable("id") Long employeeId, @PathVariable("jid") Long jobId) {
        employeeService.applyJob(employeeId, jobId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

}
