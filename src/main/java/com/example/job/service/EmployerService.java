package com.example.job.service;

import com.example.job.entity.Employer;
import com.example.job.exception.ResourceNotFoundException;
import com.example.job.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;

    public Page<Employer> find(int page, int pageSize) {
        Pageable pageable = pageSize == -1 ? Pageable.unpaged() : PageRequest.of(page, pageSize);

        return employerRepository.findAll(pageable);
    }

    public Employer update(Long id, Employer employer) {
        Employer persisted = employerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        persisted.setAddress(employer.getAddress());
        persisted.setName(employer.getName());
        persisted.setPhone(employer.getPhone());

        return employerRepository.save(persisted);
    }

    public void delete(Long id) {
        employerRepository.deleteById(id);
    }

}
