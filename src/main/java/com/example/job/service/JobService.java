package com.example.job.service;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.Employer;
import com.example.job.entity.Job;
import com.example.job.entity.JobCategory;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployerRepository;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JobService {

    private final EmployerRepository employerRepository;
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

    @PostConstruct
    public void init() {
//        create(1l, new Job(null, "title1", "description1", "location1", null, null, null, new JobCategory(1l)));
//        create(1l, new Job(null, "title2", "description2", "location2", null, null, null, new JobCategory(2l)));
        System.out.println(countByJobCategory(new JobCategory(1l)));
        System.out.println(jobRepository.findByDateBetween(LocalDate.now(), LocalDate.now().plusDays(2)));
//        System.out.println(jobRepository.searchJob(null, null, null, null));
    }

}
