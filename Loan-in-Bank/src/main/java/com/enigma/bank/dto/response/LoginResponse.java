package com.enigma.bank.dto.response;

import com.enigma.bank.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String email;
    private Set<Role.ERole> roles;
}
