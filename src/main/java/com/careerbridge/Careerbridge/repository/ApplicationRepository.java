package com.careerbridge.Careerbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.careerbridge.Careerbridge.entity.Application;
import com.careerbridge.Careerbridge.entity.Job;
import com.careerbridge.Careerbridge.entity.Student;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    
    List<Application> findByStudent(Student student);

    List<Application> findByJob(Job job);

    boolean existsByStudentAndJob(Student student,Job job);
    
}
