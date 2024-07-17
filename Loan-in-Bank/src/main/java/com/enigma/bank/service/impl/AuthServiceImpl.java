package com.enigma.bank.service.impl;

import com.enigma.bank.dto.request.LoginRequest;
import com.enigma.bank.dto.request.RegisterRequest;
import com.enigma.bank.dto.response.LoginResponse;
import com.enigma.bank.dto.response.RegisterResponse;
import com.enigma.bank.entity.AppUser;
import com.enigma.bank.entity.Role;
import com.enigma.bank.entity.User;
import com.enigma.bank.repository.UserRepository;
import com.enigma.bank.security.JwtUtil;
import com.enigma.bank.service.AuthService;
import com.enigma.bank.service.RoleService;
import com.enigma.bank.service.ValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        validationService.validate(request);
        Optional<User> i = userRepository.findByEmail(request.getEmail());
        if (i.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        System.out.println(role.getName());
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .deleted(false)
                .build();

        userRepository.save(user);
        return RegisterResponse.builder()
                .email(user.getEmail())
                .roles(Set.of(role.getName()))
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        validationService.validate(request);

        log.info(request.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .email(request.getEmail())
                .token(token)
                .roles(appUser.getRoles())
        .build();
    }
}