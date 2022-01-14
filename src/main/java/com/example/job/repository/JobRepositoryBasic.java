package com.example.job.repository;

import com.example.job.entity.Job;
import com.example.job.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepositoryBasic extends JpaRepository<Job, Long> {

    int countByJobCategory(JobCategory jobCategory);

    List<Job> findByDateBetween(LocalDate from, LocalDate to);

}
