package com.careerbridge.Careerbridge.service;

import org.springframework.stereotype.Service;

import com.careerbridge.Careerbridge.dto.AdminDashboardResponse;
import com.careerbridge.Careerbridge.repository.ApplicationRepository;
import com.careerbridge.Careerbridge.repository.CompanyRepository;
import com.careerbridge.Careerbridge.repository.JobRepository;
import com.careerbridge.Careerbridge.repository.StudentRepository;
import com.careerbridge.Careerbridge.dto.AdminStudentResponse;
import com.careerbridge.Careerbridge.dto.AdminCompanyResponse;
import com.careerbridge.Careerbridge.dto.AdminJobResponse;
import com.careerbridge.Careerbridge.dto.AdminApplicationResponse;
import com.careerbridge.Careerbridge.entity.Student;
import com.careerbridge.Careerbridge.entity.Company;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public AdminDashboardResponse getDashboard(){

        return AdminDashboardResponse.builder()
                                     .totalStudents(studentRepository.count())
                                     .totalCompanies(companyRepository.count())
                                     .totalJobs(jobRepository.count())
                                     .totalApplications(applicationRepository.count())
                                     .build();
    }

    @Override
    public List<AdminStudentResponse> getAllStudents(){

        return studentRepository.findAll()
                                .stream()
                                .map(student -> AdminStudentResponse.builder()
                                                                    .id(student.getId())
                                                                    .fullName(student.getUser().getName())
                                                                    .email(student.getUser().getEmail())
                                                                    .phone(student.getPhone())
                                                                    .college(student.getCollege())
                                                                    .branch(student.getBranch())
                                                                    .build())
                                .toList();
    }

    @Override
    public List<AdminCompanyResponse> getAllCompanies(){

        return companyRepository.findAll()
                                .stream()
                                .map(company -> AdminCompanyResponse.builder()
                                                                    .id(company.getId())
                                                                    .companyName(company.getCompanyName())
                                                                    .email(company.getCompanyName())
                                                                    .build()
                                                                )
                                .toList();
    }

    @Override
    public List<AdminJobResponse> getAllJobs(){

        return jobRepository.findAll()
                            .stream()
                            .map(job ->  AdminJobResponse.builder()
                                                         .id(job.getId())
                                                         .title(job.getTitle())
                                                         .location(job.getLocation())
                                                         .jobType(job.getJobType())
                                                         .salary(job.getSalary())
                                                         .build()
                                )
                            .toList();
    }

    @Override
    public List<AdminApplicationResponse> getAllApplications(){

        return applicationRepository.findAll()
                                    .stream()
                                    .map(application -> AdminApplicationResponse.builder()
                                                                                .id(application.getId())
                                                                                .studentName(application.getStudent().getUser().getName())
                                                                                .companyName(application.getJob().getCompany().getCompanyName())
                                                                                .jobTitle(application.getJob().getTitle())
                                                                                .status(application.getStatus().toString())
                                                                                .build()
                                    )
                                    .toList();   
    }

    @Override
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteCompany(Long id){

        companyRepository.deleteById(id);
    }

    @Override
    public void deleteJob(Long id){

        jobRepository.deleteById(id);

    }

    @Override
    public void deleteApplication(Long id){

        applicationRepository.deleteById(id);
    }


    
}
