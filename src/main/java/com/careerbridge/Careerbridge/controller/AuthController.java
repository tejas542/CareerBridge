package com.careerbridge.Careerbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.careerbridge.Careerbridge.dto.RegisterRequest;
import com.careerbridge.Careerbridge.service.AuthService;
import com.careerbridge.Careerbridge.entity.User;
import com.careerbridge.Careerbridge.dto.LoginRequest;
import com.careerbridge.Careerbridge.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }


}
