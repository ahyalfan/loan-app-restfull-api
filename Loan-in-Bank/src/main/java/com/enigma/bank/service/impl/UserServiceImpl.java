package com.enigma.bank.service.impl;

import com.enigma.bank.dto.response.UserResponse;
import com.enigma.bank.entity.AppUser;
import com.enigma.bank.entity.Role;
import com.enigma.bank.entity.User;
import com.enigma.bank.repository.RoleRepository;
import com.enigma.bank.repository.UserRepository;
import com.enigma.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid creadential user"));
        System.out.println(user.toString());
        var roles = roleRepository.findAllByUsers(user);
        var rolesName = roles.stream().map(Role::getName).collect(Collectors.toSet());
//        var rolesName = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        return AppUser.builder()
                .Id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(rolesName)
                .build();
    }

    @Override
    public AppUser loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid creadential user"));
        var roles = roleRepository.findAllByUsers(user);
        var rolesName = roles.stream().map(Role::getName).collect(Collectors.toSet());
//        var rolesName = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        return AppUser.builder()
                .Id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(rolesName)
                .build();
    }

    @Override
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid creadential user"));
        return UserResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }
}
