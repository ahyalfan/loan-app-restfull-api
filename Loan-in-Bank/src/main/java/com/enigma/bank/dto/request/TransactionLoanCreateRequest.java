package com.enigma.bank.dto.request;

import com.enigma.bank.entity.LoanType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionLoanCreateRequest {
    @NotBlank
    private String loanTypeId;
    @NotBlank
    private String InstalmentTypeId;
    @NotBlank
    private String customerId;
    @JsonProperty("nominal")
    private Double amount;

    @NotNull
    private TransactionLoanCreateDetail transactionDetail;

}
