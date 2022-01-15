package com.example.job.service;

import com.example.job.entity.Employer;
import com.example.job.entity.Job;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private static final String REDIS_CACHE = "EmployerService";

    private final EmployerRepository employerRepository;

//    @Cacheable(value = REDIS_CACHE, key = "#page.toString() + #pageSize.toString()")
    public Page<Employer> find(Integer page, Integer pageSize) {
        Pageable pageable = pageSize == -1 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

        return employerRepository.findAll(pageable);
    }

    @CacheEvict(value = REDIS_CACHE)
    public Employer update(Long id, Employer employer) {
        Employer persisted = employerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        persisted.setAddress(employer.getAddress());
        persisted.setName(employer.getName());
        persisted.setPhone(employer.getPhone());

        return employerRepository.save(persisted);
    }

    @CacheEvict(value = REDIS_CACHE)
    public void delete(Long id) {
        employerRepository.deleteById(id);
    }

    public Set<Job> findJobs(Long id) {
        return employerRepository.findById(id).map(Employer::getJobs).orElseThrow(ResourceNotFoundException::new);
    }

}
