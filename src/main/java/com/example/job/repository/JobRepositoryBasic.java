package com.example.job.repository;

import com.example.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepositoryBasic extends JpaRepository<Job, Long> {
}
