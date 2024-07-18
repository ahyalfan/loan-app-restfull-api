package com.enigma.bank.service;

import com.enigma.bank.dto.request.LoanTypeCreateRequest;
import com.enigma.bank.dto.request.LoanTypeUpdateRequest;
import com.enigma.bank.dto.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoan(LoanTypeCreateRequest request);
    List<LoanTypeResponse> getAll();
    LoanTypeResponse getById(String id);
    void deleteById(String id);
    LoanTypeResponse updateById(LoanTypeUpdateRequest request);
}
