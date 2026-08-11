package com.careerbridge.Careerbridge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.careerbridge.Careerbridge.dto.ResumeResponse;
import com.careerbridge.Careerbridge.service.ResumeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    
    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<ResumeResponse> uploadResume(@RequestParam("file") MultipartFile file){

        
         ResumeResponse response = resumeService.uploadResume(file);

            System.out.println("CONTROLLER RESPONSE = " + response);

            return ResponseEntity.ok(response);
    }

    @GetMapping("/my")
    public ResponseEntity<ResumeResponse> getMyResume(){

        return ResponseEntity.ok(resumeService.getMyResume());

    }

    @PutMapping("/update")
    public ResponseEntity<ResumeResponse> updateResume(@RequestParam("file") MultipartFile file){

        return ResponseEntity.ok(resumeService.updateResume(file));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResume(){

        resumeService.deleteResume();

        return ResponseEntity.ok("Resume Deleted Successfully");
    }

}
