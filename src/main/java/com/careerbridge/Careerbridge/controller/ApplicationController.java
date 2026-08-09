package com.careerbridge.Careerbridge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careerbridge.Careerbridge.dto.ApplicationRequest;
import com.careerbridge.Careerbridge.dto.ApplicationResponse;
import com.careerbridge.Careerbridge.enums.ApplicationStatus;
import com.careerbridge.Careerbridge.service.ApplicationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
    
    private final ApplicationService applicationService;

    // @PostMapping
    // public ResponseEntity<ApplicationResponse> applyJob(@RequestBody ApplicationRequest request){

    //     return ResponseEntity.ok(applicationService.applyJob(request));

    // }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(){

        System.out.println("===== GET MY APPLICATIONS HIT =====");

        return ResponseEntity.ok(applicationService.getMyApplications());

    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicants(@PathVariable Long  jobId){

         System.out.println("===== GET APPLICANTS HIT =====");
         
        return ResponseEntity.ok(applicationService.getApplicants(jobId));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long applicationId, @RequestParam ApplicationStatus status){
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, status));
    }

   @PostMapping
    public ResponseEntity<ApplicationResponse> applyJob(
            @RequestBody ApplicationRequest request) {

        System.out.println("===== APPLICATION CONTROLLER HIT =====");

        return ResponseEntity.ok(applicationService.applyJob(request));

    }
}
