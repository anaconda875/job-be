package com.example.job.service;

import com.example.job.entity.Employee;
import com.example.job.entity.Job;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployeeRepository;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    public void applyJob(Long employeeId, Long jobId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(ResourceNotFoundException::new);
        employee.getJobs().add(jobRepository.findById(jobId).orElseThrow(ResourceNotFoundException::new));
        employeeRepository.save(employee);
    }

    public Set<Job> findApplied(Long employeeId) {
        return employeeRepository.findById(employeeId).map(Employee::getJobs).orElseThrow(ResourceNotFoundException::new);
    }

}
