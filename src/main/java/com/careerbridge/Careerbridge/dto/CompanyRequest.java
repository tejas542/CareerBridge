package com.careerbridge.Careerbridge.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    private String companyName;

    private String website;

    private String industry;

    private String location;

    private String description;

    private String hrName;

    private String hrEmail;

    private String hrPhone;

    private String logoUrl;
    
}
