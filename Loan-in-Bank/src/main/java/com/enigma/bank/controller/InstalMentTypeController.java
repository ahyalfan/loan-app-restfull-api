package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiUrl.INSTALMENT_TYPE_API)
@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
// TODO: Implement the functionality for managing instalment types here
public class InstalMentTypeController {
    // Endpoint for managing instalment types

}
