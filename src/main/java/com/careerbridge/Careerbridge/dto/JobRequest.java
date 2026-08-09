package com.careerbridge.Careerbridge.dto;

import java.time.LocalDate;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    
    private String title;

    private String description;

    private String location;

    private String salary;

    private String experience;

    private String jobType;

    private LocalDate applicationDeadline;
    
}
