package com.careerbridge.Careerbridge.controller;

import com.careerbridge.Careerbridge.dto.CompanyRequest;
import com.careerbridge.Careerbridge.dto.CompanyResponse;
import com.careerbridge.Careerbridge.service.CompanyService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest request){

        CompanyResponse response = companyService.createCompany(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<CompanyResponse> getCompanyProfile(){

        return ResponseEntity.ok(companyService.getCompanyProfile());

    }

    @PutMapping
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody CompanyRequest request){

        return ResponseEntity.ok(companyService.updateCompany(request));

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany(){

        companyService.deleteCompany();

        return ResponseEntity.ok("Company Profile Deleted Successfully.");
    }

    
}
