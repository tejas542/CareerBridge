package com.careerbridge.Careerbridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCompanyResponse {

    private Long id;
    private String companyName;
    private String email;
    private String phone;
    private String industry;
    private String location;

}