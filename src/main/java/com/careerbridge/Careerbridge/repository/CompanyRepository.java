package com.careerbridge.Careerbridge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerbridge.Careerbridge.entity.Company;
import com.careerbridge.Careerbridge.entity.User;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByUser(User user);
    
}
