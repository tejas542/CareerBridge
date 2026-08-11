package com.careerbridge.Careerbridge.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.careerbridge.Careerbridge.dto.ResumeResponse;
import com.careerbridge.Careerbridge.entity.Resume;
import com.careerbridge.Careerbridge.entity.Student;
import com.careerbridge.Careerbridge.entity.User;
import com.careerbridge.Careerbridge.exception.ResourceNotFoundException;
import com.careerbridge.Careerbridge.repository.ResumeRepository;
import com.careerbridge.Careerbridge.repository.StudentRepository;
import com.careerbridge.Careerbridge.repository.UserRepository;
// import com.careerbridge.Careerbridge.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private static final String UPLOAD_DIR = "uploads/resumes/";

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Student getLoggedInStudent() {

        User user = getLoggedInUser();

        return studentRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public ResumeResponse uploadResume(MultipartFile file) {

        Student student = getLoggedInStudent();

        if (resumeRepository.existsByStudent(student)) {
            throw new RuntimeException("Resume already exists. Please update it.");
        }

        try {

            File directory = new File(UPLOAD_DIR);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            Resume resume = new Resume();

            resume.setStudent(student);
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setFilePath(path.toString());

           Resume savedResume = resumeRepository.save(resume);

            ResumeResponse response = mapToResponse(savedResume);

            System.out.println("ID = " + response.getId());
            System.out.println("FILE = " + response.getFileName());
            System.out.println("TYPE = " + response.getFileType());

            return response;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload resume",e);
        }
    }

    @Override
    public ResumeResponse getMyResume() {

        Student student = getLoggedInStudent();

        Resume resume = resumeRepository.findByStudent(student)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

        return mapToResponse(resume);
    }

    @Override
    public ResumeResponse updateResume(MultipartFile file) {

        Student student = getLoggedInStudent();

        Resume resume = resumeRepository.findByStudent(student)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

        try {

            Files.deleteIfExists(Paths.get(resume.getFilePath()));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setFilePath(path.toString());

            Resume updatedResume = resumeRepository.save(resume);

            return mapToResponse(updatedResume);

        } catch (IOException e) {
            throw new RuntimeException("Failed to update resume");
        }
    }

    @Override
    public void deleteResume() {

        Student student = getLoggedInStudent();

        Resume resume = resumeRepository.findByStudent(student)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

        try {
            Files.deleteIfExists(Paths.get(resume.getFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resumeRepository.delete(resume);
    }

    private ResumeResponse mapToResponse(Resume resume) {

        return ResumeResponse.builder()
                .id(resume.getId())
                .fileName(resume.getFileName())
                .fileType(resume.getFileType())
                .uploadedAt(resume.getUploadedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }
}