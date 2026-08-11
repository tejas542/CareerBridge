package com.careerbridge.Careerbridge.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponse {

    private Long id;

    private String fileName;

    private String fileType;

    private LocalDateTime uploadedAt;

    private LocalDateTime updatedAt;
}
