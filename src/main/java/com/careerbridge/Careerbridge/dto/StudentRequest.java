package com.careerbridge.Careerbridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    
    
    private String college;
    
    private String branch;

    private Integer year;

    private Double cgpa;

    private String skills;

    private String phone;

    private String address;

    private String about;

    private String linkedinUrl;

    private String githubUrl;
}
