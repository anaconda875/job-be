package com.example.job.controller;

import com.example.job.entity.ExperienceLevel;
import com.example.job.entity.JobCategory;
import com.example.job.repository.ExperienceLevelRepository;
import com.example.job.repository.JobCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master-data")
@RequiredArgsConstructor
public class MasterDataController {

    private final ExperienceLevelRepository experienceLevelRepository;
    private final JobCategoryRepository jobCategoryRepository;

    @GetMapping("/experience-levels")
    public List<ExperienceLevel> findAllExp() {
        return experienceLevelRepository.findAll();
    }

    @GetMapping("/job-categories")
    public List<JobCategory> findAllCat() {
        return jobCategoryRepository.findAll();
    }

}
