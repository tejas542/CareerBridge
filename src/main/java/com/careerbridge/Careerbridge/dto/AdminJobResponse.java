package com.careerbridge.Careerbridge.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminJobResponse {
    
    private Long id;

    private String title;

    private String location;

    private String jobType;

    private String salary;
}
