package com.enigma.bank.dto.response;

import com.enigma.bank.entity.LoanTransactionDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionLoanDetailsResponse{
    private String id;
    private Long transactionDate;
    @JsonProperty("nominal")
    private Double amount;
    private LoanTransactionDetails.LoanStatus loanType;
    private Long createdAt;
    private Long updatedAt;
}
