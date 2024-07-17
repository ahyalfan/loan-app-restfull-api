package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.request.CustomerCreateRequest;
import com.enigma.bank.dto.request.CustomerUpdateRequest;
import com.enigma.bank.dto.response.CustomerResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.entity.User;
import com.enigma.bank.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.CUSTOMER_API)
// @PreAuthorize("hasRole('ROLE_ADMIN')") // cek role admin
@SecurityRequirement(name = "Authorization")
public class CustomerController {
    private final CustomerService customerService;
    // Customer endpoints
    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> create(User user, @RequestBody CustomerCreateRequest request){
        // Implement create logic
        // Return CustomerResponse
        CustomerResponse response = customerService.create(user, request);
        return WebResponse.<CustomerResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .data(response)
                .build();
    }
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> getById(User user, @PathVariable String id){
        CustomerResponse response = customerService.getById(user, id);
        return WebResponse.<CustomerResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .data(response)
               .build();
    }
    @PatchMapping(
            path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> updateCustomer (User user,@PathVariable String id,
                                                         @RequestBody CustomerUpdateRequest request){
        request.setId(id);
        CustomerResponse response = customerService.update(user, request);
        return WebResponse.<CustomerResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .data(response)
               .build();
    }
    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> deleted(User user, String id){
        customerService.deleteById(user, id);
        return WebResponse.<String>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .message("Customer deleted successfully")
               .build();
    }
    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<CustomerResponse>> getAll(User user){
        List<CustomerResponse> response = customerService.getAll(user);
        return WebResponse.<List<CustomerResponse>>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .data(response)
               .build();
    }
}
