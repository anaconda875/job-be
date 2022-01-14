package com.example.job.service;

import com.example.job.dto.request.JobRequest;
import com.example.job.entity.Employer;
import com.example.job.entity.Job;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployerRepository;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class JobService {

    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;

    public Job postJob(Long employerId, Job job) {
        employerRepository.findById(employerId).orElseThrow(ResourceNotFoundException::new);
        job.setOwner(new Employer(employerId));

        return jobRepository.save(job);
    }

    public Page<Job> searchJob(JobRequest jobRequest) {
        Pageable pageable = jobRequest.getPageSize() == -1 ? Pageable.unpaged() : PageRequest.of(jobRequest.getPage(), jobRequest.getPageSize());

        return jobRepository.searchJob(pageable, jobRequest);
    }
    @PostConstruct
    public void init() {
//        postJob(null, new Job(null, "title1", "description1", "location1", null, null, null));
//        postJob(null, new Job(null, "title2", "description2", "location2", null, null, null));
//        System.out.println(jobRepository.searchJob(null, null, null, null));
    }

}
