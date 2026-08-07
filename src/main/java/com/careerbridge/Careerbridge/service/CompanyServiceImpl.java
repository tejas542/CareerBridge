package com.careerbridge.Careerbridge.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.security.core.Authentication;


import com.careerbridge.Careerbridge.repository.CompanyRepository;
import com.careerbridge.Careerbridge.repository.UserRepository;
import com.careerbridge.Careerbridge.dto.CompanyRequest;
import com.careerbridge.Careerbridge.dto.CompanyResponse;
import com.careerbridge.Careerbridge.entity.Company;
import com.careerbridge.Careerbridge.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private CompanyResponse mapToResponse(Company company) {

        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getUser().getName());
        response.setEmail(company.getUser().getEmail());

        response.setCompanyName(company.getCompanyName());
        response.setWebsite(company.getWebsite());
        response.setIndustry(company.getIndustry());
        response.setLocation(company.getLocation());
        response.setDescription(company.getDescription());
        response.setHrName(company.getHrName());
        response.setHrEmail(company.getHrEmail());
        response.setHrPhone(company.getHrPhone());
        response.setLogoUrl(company.getLogoUrl());

        return response;
    }
    

    @Override
    public CompanyResponse createCompany(CompanyRequest request){

        User user = getLoggedInUser();

        Optional<Company> optionalCompany = companyRepository.findByUser(user);

        if(optionalCompany.isPresent()){

            throw new RuntimeException("Company profile Already exists");
        }

        Company company = new Company();

        company.setCompanyName(request.getCompanyName());
        company.setUser(user);
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());
        company.setDescription(request.getDescription());
        company.setHrEmail(request.getHrEmail());
        company.setHrName(request.getHrName());
        company.setHrPhone(request.getHrPhone());
        company.setLogoUrl(request.getLogoUrl());

        Company savedCompany = companyRepository.save(company);

        return mapToResponse(savedCompany);
    }

    @Override
    public CompanyResponse getCompanyProfile(){
        User user = getLoggedInUser();

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Company profile not found"));

        return mapToResponse(company);
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest request){
        User user = getLoggedInUser();

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Company profile not found"));

        company.setCompanyName(request.getCompanyName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());
        company.setDescription(request.getDescription());
        company.setHrName(request.getHrName());
        company.setHrEmail(request.getHrEmail());
        company.setHrPhone(request.getHrPhone());
        company.setLogoUrl(request.getLogoUrl());

        Company updatedCompany = companyRepository.save(company);

        return mapToResponse(updatedCompany);
    }

    @Override
    public void deleteCompany(){
        User user = getLoggedInUser();

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Company profile not found"));

        companyRepository.delete(company);    
    }

}
