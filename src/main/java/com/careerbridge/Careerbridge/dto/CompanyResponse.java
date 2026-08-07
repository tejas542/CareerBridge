package com.careerbridge.Careerbridge.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {

    private Long id;

    private String name;

    private String email;

    private String companyName;

    private String website;

    private String industry;

    private String location;

    private String description;

    private String hrName;

    private String hrPhone;

    private String hrEmail;

    private String logoUrl;

}
