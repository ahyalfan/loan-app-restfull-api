package com.enigma.bank.service;

import com.enigma.bank.dto.request.LoginRequest;
import com.enigma.bank.dto.request.RegisterRequest;
import com.enigma.bank.dto.response.LoginResponse;
import com.enigma.bank.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request,boolean admin);

    LoginResponse login(LoginRequest request);
}
