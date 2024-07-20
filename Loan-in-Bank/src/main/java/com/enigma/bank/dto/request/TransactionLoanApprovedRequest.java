package com.enigma.bank.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLoanApprovedRequest {
    @NotBlank
    private String adminId;
    @NotBlank
    private String loanId;
    private Integer interestRates;
}
