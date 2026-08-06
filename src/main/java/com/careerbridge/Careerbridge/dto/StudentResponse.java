package com.careerbridge.Careerbridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;

    private String name;

    private String email;

    private String college;

    private String branch;

    private String skills;

    private Integer year;

    private Double cgpa;

    private String phone;

    private String address;

    private String about;

    private String resumeUrl;

    private String profilePhoto;

    private String linkedinUrl;

    private String githubUrl;
    
}
