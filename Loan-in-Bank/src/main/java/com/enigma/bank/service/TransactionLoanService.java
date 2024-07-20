package com.enigma.bank.service;

import com.enigma.bank.dto.request.TransactionLoanApprovedRequest;
import com.enigma.bank.dto.request.TransactionLoanCreateDetail;
import com.enigma.bank.dto.request.TransactionLoanCreateRequest;
import com.enigma.bank.dto.response.TransactionLoanResponse;
import com.enigma.bank.entity.Customer;
import com.enigma.bank.entity.User;

public interface TransactionLoanService {
    TransactionLoanResponse create(User user, TransactionLoanCreateRequest createRequest);
    TransactionLoanResponse getById(String id);
    TransactionLoanResponse approved(User user, TransactionLoanApprovedRequest approvalRequest);
}
