package com.careerbridge.Careerbridge.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.careerbridge.Careerbridge.dto.*;
import com.careerbridge.Careerbridge.entity.*;
import com.careerbridge.Careerbridge.enums.ApplicationStatus;
import com.careerbridge.Careerbridge.repository.ApplicationRepository;
import com.careerbridge.Careerbridge.repository.JobRepository;
import com.careerbridge.Careerbridge.repository.StudentRepository;
import com.careerbridge.Careerbridge.repository.UserRepository;
import com.careerbridge.Careerbridge.repository.CompanyRepository;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    
    private final JobRepository jobRepository;

    private final StudentRepository studentRepository;

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private Student getLoggedInStudent(){

        User user = getLoggedInUser();

        return studentRepository.findByUser(user).orElseThrow(() -> 
        new RuntimeException("Student profilee not found"));

    }

    private Company getLoggedInCompany(){

        User user = getLoggedInUser();

        return companyRepository.findByUser(user).orElseThrow(() -> 
        new RuntimeException("Company profile not found"));

    }

    private ApplicationResponse maptoResponse(Application application){

        return ApplicationResponse.builder()
                                  .id(application.getId())
                                  .studentName(application.getStudent().getUser().getName())
                                  .companyName(application.getJob().getCompany().getCompanyName())
                                  .jobTitle(application.getJob().getTitle())
                                  .status(application.getStatus())
                                  .appliedAt(application.getAppliedAt())
                                  .build();
    }

    @Override
    public ApplicationResponse applyJob(ApplicationRequest request){

        Student student = getLoggedInStudent();

        Job job = jobRepository.findById(request.getJobId()).orElseThrow(() -> new RuntimeException("Job not Found"));

        if(applicationRepository.existsByStudentAndJob(student, job)){

            throw new RuntimeException("You have already applied for this job");
        }

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);

        Application savedApplication = applicationRepository.save(application);

        return maptoResponse(savedApplication);
    }

    @Override
    public List<ApplicationResponse> getMyApplications(){

        Student student = getLoggedInStudent();

        return applicationRepository.findByStudent(student)
                                    .stream()
                                    .map(this::maptoResponse)
                                    .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicants(Long jobId){

        Company company = getLoggedInCompany();

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not Found"));

        if(!job.getCompany().getId().equals(company.getId())){
            throw new RuntimeException("You are not authorized");
        }

        return applicationRepository.findByJob(job)
                                    .stream()
                                    .map(this::maptoResponse)
                                    .toList();
    }

    @Override 
    public ApplicationResponse updateStatus(Long applicationId,ApplicationStatus status){

        Company company = getLoggedInCompany();

        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application Not Found"));

        if(!application.getJob().getCompany().getId().equals(company.getId())){
            
            throw new RuntimeException("You are not Authorized");
        }

        application.setStatus(status);

        Application updateApplication = applicationRepository.save(application);

        return maptoResponse(updateApplication);
    }
    
}
