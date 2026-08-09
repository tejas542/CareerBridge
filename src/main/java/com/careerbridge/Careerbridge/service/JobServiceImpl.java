package com.careerbridge.Careerbridge.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

import org.springframework.security.core.Authentication;


import com.careerbridge.Careerbridge.repository.CompanyRepository;
import com.careerbridge.Careerbridge.repository.JobRepository;
import com.careerbridge.Careerbridge.repository.UserRepository;
import com.careerbridge.Careerbridge.dto.CompanyRequest;
import com.careerbridge.Careerbridge.dto.CompanyResponse;
import com.careerbridge.Careerbridge.dto.JobRequest;
import com.careerbridge.Careerbridge.dto.JobResponse;
import com.careerbridge.Careerbridge.entity.Company;
import com.careerbridge.Careerbridge.entity.User;
import com.careerbridge.Careerbridge.entity.Job;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;
    
    private final JobRepository jobRepository;

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Company getLoggedInCompany() {

        User user = getLoggedInUser();

        return companyRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));
    }

    private JobResponse mapToResponse(Job job) {

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .applicationDeadline(job.getApplicationDeadline())
                .createdAt(job.getCreatedAt())
                .status(job.getStatus())
                .companyName(job.getCompany().getCompanyName())
                .companyLocation(job.getCompany().getLocation())
                .build();
    }
    @Override
    public JobResponse createJob(JobRequest request){

        Company company = getLoggedInCompany();
       
        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setExperience(request.getExperience());
        job.setJobType(request.getJobType());
        job.setApplicationDeadline(request.getApplicationDeadline());

        job.setCompany(company);

        Job savedJob = jobRepository.save(job);
        
        return mapToResponse(savedJob);
    }

    @Override
    public List<JobResponse> getAllJobs(){

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                    .map(this::mapToResponse)
                    .toList();
    }

    @Override
    public JobResponse getJobById(Long id){
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));

        return mapToResponse(job);
    }

    @Override
    public List<JobResponse> getMyJobs(){

        Company company = getLoggedInCompany();

        List<Job> jobs = jobRepository.findByCompany(company);

        return jobs.stream()
                .map(this::mapToResponse)
                .toList();


    }

    @Override
    public JobResponse updateJob(Long id,JobRequest request){

        Company company = getLoggedInCompany();

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException("You are not authorized to update this job");
        }

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setExperience(request.getExperience());
        job.setJobType(request.getJobType());
        job.setApplicationDeadline(request.getApplicationDeadline());

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);

    }

    @Override
    public void deleteJob(Long id){

        Company company = getLoggedInCompany();

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException("You are not authorized to delete this job");
        }

        jobRepository.delete(job);
    }
}
