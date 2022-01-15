package com.example.job.service;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.EmployeeApplied;
import com.example.job.entity.Employer;
import com.example.job.entity.Job;
import com.example.job.entity.JobCategory;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployeeAppliedRepository;
import com.example.job.repository.EmployerRepository;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final EmployerRepository employerRepository;
    private final EmployeeAppliedRepository employeeAppliedRepository;
    private final JobRepository jobRepository;

    public Job create(Long employerId, Job job) {
        employerRepository.findById(employerId).orElseThrow(ResourceNotFoundException::new);
        job.setOwner(new Employer(employerId));
        job.setDate(LocalDate.now());

        return jobRepository.save(job);
    }

    public Page<Job> find(JobFilter jobFilter) {
        Pageable pageable = jobFilter.getPageSize() == -1 ? Pageable.unpaged() : PageRequest.of(jobFilter.getPage(), jobFilter.getPageSize());

        return jobRepository.find(pageable, jobFilter);
    }

    public Job update(Long id, Job job) {
        Job persisted = jobRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        persisted.setTitle(job.getTitle());
        persisted.setDescription(job.getDescription());
        persisted.setLocation(job.getLocation());
        persisted.setExperienceLevel(job.getExperienceLevel());
        persisted.setJobCategory(job.getJobCategory());

        return jobRepository.save(persisted);
    }

    public int countByJobCategory(JobCategory jobCategory) {
        return jobRepository.countByJobCategory(jobCategory);
    }

    public Map<LocalDate, Long> countByDates(LocalDate from, LocalDate to) {
        return jobRepository.findByDateBetween(from, to).stream().collect(Collectors.groupingBy(Job::getDate, Collectors.counting()));
    }

    public Map<LocalDate, Long> countAppliedByDates(LocalDate from, LocalDate to) {
        Map<LocalDate, List<EmployeeApplied>> appliedByDates = employeeAppliedRepository.findByDateBetween(from, to).stream().collect(Collectors.groupingBy(EmployeeApplied::getDate));

        Map<LocalDate, Long> countByDates = new HashMap<>();
        appliedByDates.forEach((date, applied) -> {
            countByDates.put(date, applied.stream().map(EmployeeApplied::getJob).distinct().count());
        });

        return countByDates;
    }

    @PostConstruct
    public void init() {
//        create(1l, new Job(null, "title1", "description1", "location1", null, null, null, new JobCategory(1l)));
//        create(1l, new Job(null, "title2", "description2", "location2", null, null, null, new JobCategory(2l)));
//        System.out.println(countByJobCategory(new JobCategory(1l)));
//        System.out.println(countByDates(LocalDate.now(), LocalDate.now().plusDays(1)));
//        System.out.println(jobRepository.searchJob(null, null, null, null));
    }

}
