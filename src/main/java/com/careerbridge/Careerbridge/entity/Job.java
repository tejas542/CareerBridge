package com.careerbridge.Careerbridge.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private String salary;

    private String experience;

    private  String JobType;

    private LocalDate applicationDeadline;

    private LocalDateTime createdAt;

    private String status;

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        status = "OPEN";
    }



}


