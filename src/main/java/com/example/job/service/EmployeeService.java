package com.example.job.service;

import com.example.job.entity.Employee;
import com.example.job.entity.EmployeeApplied;
import com.example.job.entity.Job;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployeeAppliedRepository;
import com.example.job.repository.EmployeeRepository;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeAppliedRepository employeeAppliedRepository;
    private final JobRepository jobRepository;

    public Page<Employee> find(int page, int pageSize) {
        Pageable pageable = pageSize == -1 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

        return employeeRepository.findAll(pageable);
    }

    public void applyJob(Long employeeId, Long jobId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(ResourceNotFoundException::new);
        Job job = jobRepository.findById(jobId).orElseThrow(ResourceNotFoundException::new);
        employeeAppliedRepository.save(new EmployeeApplied(null, LocalDate.now(), employee, job));
    }

    public Set<Job> findApplied(Long employeeId) {
        return employeeRepository.findById(employeeId).map(Employee::getJobs).orElseThrow(ResourceNotFoundException::new)
                .stream().map(EmployeeApplied::getJob).collect(Collectors.toSet());
    }

    public Employee update(Long id, Employee employee) {
        Employee persisted = employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        persisted.setAddress(employee.getAddress());
        persisted.setName(employee.getName());
        persisted.setPhone(employee.getPhone());
        persisted.setLocation(employee.getLocation());

        return employeeRepository.save(persisted);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
