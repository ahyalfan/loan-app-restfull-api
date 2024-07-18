package com.enigma.bank.controller;

import com.enigma.bank.dto.response.UserResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // User endpoints
    // Get user by id
    @GetMapping("{id}")
    public WebResponse<UserResponse> getUserById(@PathVariable String id) {

        var response =  userService.getUser(id);
        return WebResponse.<UserResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .message("User retrieved successfully")
                .data(response)
                .build();
    }
}
