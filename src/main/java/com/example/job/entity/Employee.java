package com.example.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String location;

    private String pin;

    @ManyToOne
    @JoinColumn(name = "experience_level_id")
    private ExperienceLevel experienceLevel;

    @ManyToMany
    @JoinTable(name = "tbl_employee_job_category",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "job_category_id"))
    @EqualsAndHashCode.Exclude
    private Set<JobCategory> jobCategories;

//    @ManyToMany
//    @JoinTable(name = "tbl_employee_applied",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "job_category"))
//    @EqualsAndHashCode.Exclude
//    @JsonIgnore
//    private Set<Job> jobs;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<EmployeeApplied> jobs;

}
