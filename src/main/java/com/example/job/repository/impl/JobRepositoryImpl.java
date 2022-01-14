package com.example.job.repository.impl;

import com.example.job.dto.request.JobFilter;
import com.example.job.entity.ExperienceLevel;
import com.example.job.entity.Job;
import com.example.job.entity.JobCategory;
import com.example.job.repository.JobRepositoryBasic;
import com.example.job.repository.JobRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl implements JobRepositoryCustom {

    private final JobRepositoryBasic jobRepositoryBasic;
    private final EntityManager entityManager;

    @Override
    public Page<Job> find(Pageable pageable, JobFilter jobFilter) {
        String sql = "SELECT job FROM Job job WHERE 1=1";
        Map<String, Object> params = new HashMap<>();
        JobCategory jobCategory = jobFilter.getJobCategory();
        if(jobCategory != null && jobCategory.getId() != null) {
            sql += " AND job.jobCategory.id = :jcid";
            params.put("jcid", jobCategory.getId());
        }

        String location = jobFilter.getLocation();
        if(location != null && !location.isBlank()) {
            sql += " AND job.location LIKE :loc";
            params.put("loc", "%" + location + "%");
        }

        ExperienceLevel experienceLevel = jobFilter.getExperienceLevel();
        if(experienceLevel != null && experienceLevel.getId() != null) {
            sql += " AND job.experienceLevel.id = :elid";
            params.put("elid", experienceLevel.getId());
        }

        String keyword = jobFilter.getKeyword();
        if(keyword != null && !keyword.isBlank()) {
            sql += " AND (job.title LIKE :kw OR job.description LIKE :kw)";
            params.put("kw", "%" + keyword + "%");
        }

        if(pageable.isUnpaged()) {
            TypedQuery<Job> query = entityManager.createQuery(sql, Job.class);
            this.setParameters(query, params);
            List<Job> resultList = query.getResultList();
            return new PageImpl<>(resultList, pageable, resultList.size());
        }

        String countSql = "SELECT COUNT(*) " + sql.substring(sql.indexOf("FROM"));
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);
        setParameters(countQuery, params);
        long total = countQuery.getSingleResult();

        List<Job> resultList = new ArrayList<>();
        if (total > 0) {
            TypedQuery<Job> query = entityManager.createQuery(sql, Job.class);
            setParameters(query, params);
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            resultList = query.getResultList();
        }

        return new PageImpl<>(resultList, pageable, total);
    }

    private void setParameters(Query query, Map<String, Object> filterParameters) {
        filterParameters.forEach(query::setParameter);
    }

}
