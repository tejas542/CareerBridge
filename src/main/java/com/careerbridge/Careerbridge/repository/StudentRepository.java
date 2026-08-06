package com.careerbridge.Careerbridge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerbridge.Careerbridge.entity.Student;
import com.careerbridge.Careerbridge.entity.User;


public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByUser(User user);
    
}
