package com.careerbridge.Careerbridge.dto;

// import org.springframework.security.web.firewall.StrictHttpFirewall;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;
    
}
