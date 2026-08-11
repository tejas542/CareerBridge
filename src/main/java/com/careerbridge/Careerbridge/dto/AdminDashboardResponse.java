package com.careerbridge.Careerbridge.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponse {

    
    private long totalStudents;

    private long totalCompanies;

    private long totalJobs;

    private long totalApplications;
}
