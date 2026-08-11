package com.careerbridge.Careerbridge.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminApplicationResponse {
    
    private Long id;

    private String studentName;

    private String companyName;

    private String jobTitle;

    private String status;
    
}
