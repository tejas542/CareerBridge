package com.careerbridge.Careerbridge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careerbridge.Careerbridge.dto.StudentRequest;
import com.careerbridge.Careerbridge.dto.StudentResponse;
import com.careerbridge.Careerbridge.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @RequestBody StudentRequest request) {

        StudentResponse response = studentService.createStudent(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StudentResponse> getStudentProfile() {

        return ResponseEntity.ok(studentService.getStudentProfile());
    }

    @PutMapping
    public ResponseEntity<StudentResponse> updateStudent(
            @RequestBody StudentRequest request) {

        return ResponseEntity.ok(studentService.updateStudent(request));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent() {

        studentService.deleteStudent();

        return ResponseEntity.ok("Student profile deleted successfully.");
    }
}