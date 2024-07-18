package com.enigma.bank.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanTypeUpdateRequest {
    @JsonIgnore
    private String id;
    private String type;
    private Double maxLoan;
}
