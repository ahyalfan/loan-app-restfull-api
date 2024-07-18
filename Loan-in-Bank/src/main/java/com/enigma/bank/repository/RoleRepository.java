package com.enigma.bank.repository;

import com.enigma.bank.entity.Role;
import com.enigma.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(Role.ERole role);
    Set<Role> findAllByUsers(User user);
}