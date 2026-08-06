package com.careerbridge.Careerbridge.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import com.careerbridge.Careerbridge.dto.StudentRequest;
import com.careerbridge.Careerbridge.dto.StudentResponse;
import com.careerbridge.Careerbridge.repository.StudentRepository;
import com.careerbridge.Careerbridge.repository.UserRepository;
import com.careerbridge.Careerbridge.service.StudentService;
import com.careerbridge.Careerbridge.entity.User;
import com.careerbridge.Careerbridge.entity.Student;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    @Override
    public StudentResponse createStudent(StudentRequest request){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new RuntimeException("User not found"));
    
    
        Optional<Student> OptionalStudent =studentRepository.findByUser(user);

        if(OptionalStudent.isPresent()){
            throw new RuntimeException("Student profile already exists");
        }

        Student student = new Student();
        student.setUser(user);
        student.setCollege(request.getCollege());
        student.setBranch(request.getBranch());
        student.setYear(request.getYear());
        student.setCgpa(request.getCgpa());
        student.setSkills(request.getSkills());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setAbout(request.getAbout());
        student.setLinkedinUrl(request.getLinkedinUrl());
        student.setGithubUrl(request.getGithubUrl());

        Student savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentProfile(){
        User user = getLoggedInUser();

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return mapToResponse(student);
    }

    @Override
    public StudentResponse updateStudent(StudentRequest request){
        User user = getLoggedInUser();

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        student.setCollege(request.getCollege());
        student.setBranch(request.getBranch());
        student.setYear(request.getYear());
        student.setCgpa(request.getCgpa());
        student.setSkills(request.getSkills());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setAbout(request.getAbout());
        student.setLinkedinUrl(request.getLinkedinUrl());
        student.setGithubUrl(request.getGithubUrl());

        Student updatedStudent = studentRepository.save(student);

        return mapToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(){
        User user = getLoggedInUser();

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        studentRepository.delete(student);

    }

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private StudentResponse mapToResponse(Student student) {

    StudentResponse response = new StudentResponse();

    response.setId(student.getId());
    response.setName(student.getUser().getName());
    response.setEmail(student.getUser().getEmail());

    response.setCollege(student.getCollege());
    response.setBranch(student.getBranch());
    response.setYear(student.getYear());
    response.setCgpa(student.getCgpa());
    response.setSkills(student.getSkills());
    response.setPhone(student.getPhone());
    response.setAddress(student.getAddress());
    response.setAbout(student.getAbout());
    response.setResumeUrl(student.getResumeUrl());
    response.setProfilePhoto(student.getProfilePhoto());
    response.setLinkedinUrl(student.getLinkedinUrl());
    response.setGithubUrl(student.getGithubUrl());

    return response;
}
    
}
