package com.enigma.bank.dto.response;

import com.enigma.bank.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class RegisterResponse {
    private String email;
    private Set<Role.ERole> roles;
}
