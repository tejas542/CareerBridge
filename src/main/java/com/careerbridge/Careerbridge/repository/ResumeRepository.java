package com.careerbridge.Careerbridge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerbridge.Careerbridge.entity.Resume;
import com.careerbridge.Careerbridge.entity.Student;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
    
    Optional<Resume> findByStudent(Student student);

    boolean existsByStudent(Student student);
}
