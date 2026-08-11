package com.careerbridge.Careerbridge.service;

import org.springframework.web.multipart.MultipartFile;

import com.careerbridge.Careerbridge.dto.ResumeResponse;


public interface ResumeService {
    
    ResumeResponse uploadResume(MultipartFile file);

    ResumeResponse getMyResume();

    ResumeResponse updateResume(MultipartFile file);

    void deleteResume();
}
