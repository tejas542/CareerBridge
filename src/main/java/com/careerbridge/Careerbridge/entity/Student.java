package com.careerbridge.Careerbridge.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "students")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;

    @Column(nullable = false)
    private String college;
    
    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private Integer year;

    private Double cgpa;

    @Column(length = 1000)
    private String skills;

    private String phone;

    private String address;

    @Column(length = 1000)
    private String about;

    private String resumeUrl;
    
    private String profilePhoto;

    private String linkedinUrl;

    private String githubUrl;

    

    
}
