package com.enigma.bank.controller;

import com.enigma.bank.constant.ApiUrl;
import com.enigma.bank.dto.request.LoanTypeCreateRequest;
import com.enigma.bank.dto.request.LoanTypeUpdateRequest;
import com.enigma.bank.dto.response.LoanTypeResponse;
import com.enigma.bank.dto.response.WebResponse;
import com.enigma.bank.service.LoanTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiUrl.LOAN_TYPE)
@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor

public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public WebResponse<LoanTypeResponse> create(@RequestBody LoanTypeCreateRequest loanType) {
        LoanTypeResponse response = loanTypeService.createLoan(loanType);
        return WebResponse.<LoanTypeResponse>builder()
                .statusCode(HttpStatusCode.valueOf(201))
                .message("Loan Type created successfully")
                .data(response)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<LoanTypeResponse>> getAll() {
        List<LoanTypeResponse> response = loanTypeService.getAll();
        return WebResponse.<List<LoanTypeResponse>>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("All loan types retrieved successfully")
               .data(response)
               .build();
    }
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<LoanTypeResponse> get(@PathVariable String id) {
        LoanTypeResponse response = loanTypeService.getById(id);
        return WebResponse.<LoanTypeResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("Loan Type retrieved successfully")
               .data(response)
               .build();
    }
    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable String id) {
        loanTypeService.deleteById(id);
        return WebResponse.<String>builder()
               .statusCode(HttpStatusCode.valueOf(200))
               .message("Loan Type deleted successfully")
                .data("OK")
               .build();
    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<LoanTypeResponse> update(@PathVariable String id, @RequestBody LoanTypeUpdateRequest request) {
        request.setId(id);
        LoanTypeResponse response = loanTypeService.updateById(request);
        return WebResponse.<LoanTypeResponse>builder()
               .statusCode(HttpStatusCode.valueOf(200))
                .message("Loan Type updated successfully")
               .data(response)
               .build();
    }
}
