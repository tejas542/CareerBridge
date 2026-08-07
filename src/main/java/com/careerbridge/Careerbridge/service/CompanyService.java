package com.careerbridge.Careerbridge.service;

import com.careerbridge.Careerbridge.dto.CompanyRequest;
import com.careerbridge.Careerbridge.dto.CompanyResponse;

public interface CompanyService {
    
    CompanyResponse createCompany(CompanyRequest request);

    CompanyResponse getCompanyProfile();

    CompanyResponse updateCompany(CompanyRequest request);

    void deleteCompany();
}
