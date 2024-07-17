package com.enigma.bank.service.impl;

import com.enigma.bank.entity.Role;
import com.enigma.bank.repository.RoleRepository;
import com.enigma.bank.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.html.HTMLTableCellElement;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public Role getOrSave(Role.ERole role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);
        // role available return it
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        // role not available create new
        Role currentRole = Role.builder()
                .name(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}
