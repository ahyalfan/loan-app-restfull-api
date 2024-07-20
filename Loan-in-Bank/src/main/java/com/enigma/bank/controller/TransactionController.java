package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.request.TransactionLoanApprovedRequest;
import com.enigma.bank.dto.request.TransactionLoanCreateRequest;
import com.enigma.bank.dto.response.TransactionLoanResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.entity.User;
import com.enigma.bank.service.TransactionLoanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TRANSACTION_API)
@SecurityRequirement(name = "Authorization")
public class TransactionController {
    private final TransactionLoanService transactionLoanService;
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionLoanResponse> post(User user, @RequestBody TransactionLoanCreateRequest request){
        var response = transactionLoanService.create(user,request);
        return WebResponse.<TransactionLoanResponse>builder()
                .statusCode(HttpStatusCode.valueOf(201))
                .message("Transaction created successfully")
               .data(response)
                .build();
    }

    @GetMapping(
            path = "/{transactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionLoanResponse> get(@PathVariable String transactionId){
        var response = transactionLoanService.getById(transactionId);
        return WebResponse.<TransactionLoanResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("Transaction retrieved successfully")
               .data(response)
               .build();
    }

    @PutMapping(
            path = "/{adminId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionLoanResponse> put(@PathVariable String adminId, User user,@RequestBody TransactionLoanApprovedRequest request){
        request.setAdminId(adminId);
        var response = transactionLoanService.approved(user,request);
        return WebResponse.<TransactionLoanResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("Transaction updated successfully")
               .data(response)
               .build();
    }

    @PutMapping(
            path = "/{transactionId}/pay",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionLoanResponse> pay(@PathVariable String transactionId){
        return WebResponse.<TransactionLoanResponse>builder()
                .statusCode(HttpStatusCode.valueOf(200))
                .message("OK")
                .data(null)
                .build();
    }
}
