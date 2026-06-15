package com.example.flowforge.service;

import com.example.flowforge.dto.request.LoginRequest;
import com.example.flowforge.dto.request.RegisterRequest;
import com.example.flowforge.dto.response.LoginResponse;
import com.example.flowforge.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}