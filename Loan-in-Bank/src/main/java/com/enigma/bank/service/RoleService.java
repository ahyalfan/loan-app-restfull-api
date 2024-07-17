package com.enigma.bank.service;

import com.enigma.bank.entity.Role;

public interface RoleService {
    Role getOrSave(Role.ERole role);
}
