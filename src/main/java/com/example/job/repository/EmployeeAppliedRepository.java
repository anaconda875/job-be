package com.example.job.repository;

import com.example.job.entity.EmployeeApplied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface EmployeeAppliedRepository extends JpaRepository<EmployeeApplied, Long> {

    Set<EmployeeApplied> findByDateBetween(LocalDate from, LocalDate to);

}
