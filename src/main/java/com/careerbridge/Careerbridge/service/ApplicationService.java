package com.careerbridge.Careerbridge.service;

import java.util.List;

import com.careerbridge.Careerbridge.dto.ApplicationRequest;
import com.careerbridge.Careerbridge.dto.ApplicationResponse;
import com.careerbridge.Careerbridge.enums.ApplicationStatus;


public interface ApplicationService {
    
    ApplicationResponse applyJob(ApplicationRequest request);

    List<ApplicationResponse> getMyApplications();

    List<ApplicationResponse> getApplicants(Long jobId);

    ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status);

}
