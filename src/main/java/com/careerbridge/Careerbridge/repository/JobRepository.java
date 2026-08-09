package com.careerbridge.Careerbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerbridge.Careerbridge.entity.Company;
import com.careerbridge.Careerbridge.entity.Job;
import com.careerbridge.Careerbridge.entity.User;


public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findByCompany(Company company);
}
