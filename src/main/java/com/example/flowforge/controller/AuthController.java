package com.example.flowforge.controller;

import com.example.flowforge.dto.request.LoginRequest;
import com.example.flowforge.dto.request.RegisterRequest;
import com.example.flowforge.dto.response.ApiResponse;
import com.example.flowforge.dto.response.LoginResponse;
import com.example.flowforge.dto.response.UserResponse;
import com.example.flowforge.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse user = authService.register(request);

        return new ApiResponse<>(
                true,
                "User registered successfully!",
                user
        );
    }
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse user = authService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful!",
                user
        );
    }
}