package com.careerbridge.Careerbridge.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
   
    private Long id;

    private String title;

    private String description;

    private String location;

    private String salary;

    private String experience;

    private String jobType;

    private LocalDate applicationDeadline;

    private LocalDateTime createdAt;

    private String status;

    private String companyName;

    private String companyLocation;
}
