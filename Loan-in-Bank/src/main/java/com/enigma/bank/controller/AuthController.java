package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.request.LoginRequest;
import com.enigma.bank.dto.request.RegisterRequest;
import com.enigma.bank.dto.response.LoginResponse;
import com.enigma.bank.dto.response.RegisterResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiUrl.AUTH_API)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    // Login and Registration methods
    @PostMapping(
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json"
    )
    public WebResponse<RegisterResponse> register(@RequestBody RegisterRequest request) {
        var response = authService.register(request);
        return WebResponse.<RegisterResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .message("User registered successfully")
                .data(response)
                .build();
    }
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json"
    )
    public WebResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        // Implement login logic
        // Return JWT token
        var response = authService.login(request);
        return WebResponse.<LoginResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .message("User logged in successfully")
                .data(response)
                .build();
    }
}
