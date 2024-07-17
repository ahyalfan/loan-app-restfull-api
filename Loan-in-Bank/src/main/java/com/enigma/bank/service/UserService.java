package com.enigma.bank.service;

import com.enigma.bank.dto.response.UserResponse;
import com.enigma.bank.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);
    UserResponse getUser(String id);

}
