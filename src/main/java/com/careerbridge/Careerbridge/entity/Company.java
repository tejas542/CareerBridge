package com.careerbridge.Careerbridge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;

    @Column(nullable = false)
    private String companyName;

    private String website;

    private String industry;

    private String location;

    @Column(length = 1000)
    private String description;

    private String hrName;

    private String hrEmail;

    private String hrPhone;

    private String logoUrl;
}
