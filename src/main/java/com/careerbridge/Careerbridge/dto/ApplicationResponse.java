package com.careerbridge.Careerbridge.dto;

import java.time.LocalDateTime;

import com.careerbridge.Careerbridge.enums.ApplicationStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    
    private Long id;

    private String studentName;

    private String companyName;

    private String jobTitle;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
