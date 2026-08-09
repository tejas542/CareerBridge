package com.careerbridge.Careerbridge.service;

import java.util.List;

import com.careerbridge.Careerbridge.dto.JobRequest;
import com.careerbridge.Careerbridge.dto.JobResponse;

public interface JobService {
    
    JobResponse createJob(JobRequest request);

    List<JobResponse> getAllJobs();

    JobResponse getJobById(Long id);

    List<JobResponse> getMyJobs();

    JobResponse updateJob(Long id,JobRequest request);

    void deleteJob(Long id);


}
