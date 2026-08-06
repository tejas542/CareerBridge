package com.careerbridge.Careerbridge.service;

import com.careerbridge.Careerbridge.dto.StudentRequest;
import com.careerbridge.Careerbridge.dto.StudentResponse;

public interface StudentService {
    
    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudentProfile();

    StudentResponse updateStudent(StudentRequest request);

    void deleteStudent();
    
}
