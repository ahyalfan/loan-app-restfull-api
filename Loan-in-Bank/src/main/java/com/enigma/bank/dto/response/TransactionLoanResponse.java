package com.enigma.bank.dto.response;

import com.enigma.bank.entity.Loan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLoanResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;

    @JsonProperty("nominal")
    private Double amount;
    private Long approvedAt;
    private String approvedBy;
    private Loan.ApprovalStatus approvalStatus;
    private TransactionLoanDetailsResponse transactionLoanDetailsResponse;
    private Long updatedAt;
    private Long createdAt;
}
