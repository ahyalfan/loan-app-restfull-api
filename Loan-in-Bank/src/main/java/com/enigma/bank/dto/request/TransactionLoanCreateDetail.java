package com.enigma.bank.dto.request;

import com.enigma.bank.entity.LoanTransactionDetails;
import jakarta.validation.constraints.Min;
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
public class TransactionLoanCreateDetail {
    @Min(0)
    private Double amount;
    @NotNull
    private LoanTransactionDetails.LoanStatus loan_status;
}
