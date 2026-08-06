package com.careerbridge.Careerbridge.service;

import com.careerbridge.Careerbridge.dto.RegisterRequest;
import com.careerbridge.Careerbridge.entity.User;
import com.careerbridge.Careerbridge.dto.LoginRequest;
import com.careerbridge.Careerbridge.dto.LoginResponse;

public interface AuthService {
    
    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
