package com.careerbridge.Careerbridge.service;

import java.util.List;

import com.careerbridge.Careerbridge.dto.AdminApplicationResponse;
import com.careerbridge.Careerbridge.dto.AdminCompanyResponse;
import com.careerbridge.Careerbridge.dto.AdminDashboardResponse;
import com.careerbridge.Careerbridge.dto.AdminJobResponse;
import com.careerbridge.Careerbridge.dto.AdminStudentResponse;


public interface AdminService {
    
    AdminDashboardResponse getDashboard();

    List<AdminStudentResponse> getAllStudents();

    List<AdminCompanyResponse> getAllCompanies();

    List<AdminJobResponse> getAllJobs();

    List<AdminApplicationResponse> getAllApplications();

    void deleteStudent(Long id);

    void deleteCompany(Long id);

    void deleteJob(Long id);

    void deleteApplication(Long id);
}
