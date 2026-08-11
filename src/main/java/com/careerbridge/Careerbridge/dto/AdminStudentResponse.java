package com.careerbridge.Careerbridge.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStudentResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String college;

    private String course;

    private String branch;

    private String graduationYear;
    
}
