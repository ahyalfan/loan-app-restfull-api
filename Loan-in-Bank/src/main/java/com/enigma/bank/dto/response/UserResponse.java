package com.enigma.bank.dto.response;

import com.enigma.bank.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String email;
    private Set<Role.ERole> roles;
}
